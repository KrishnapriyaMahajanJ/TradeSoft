package com.tradesoft.exchanges.client;

import com.tradesoft.exchanges.dto.request.ExchangeRequest;
import com.tradesoft.exchanges.dto.response.ExchangeResponse;
import com.tradesoft.exchanges.dto.response.clientResponse.BlockchainSymbolsResponse;
import com.tradesoft.exchanges.utils.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.tradesoft.exchanges.utils.Constants.MAX_SYMBOLS_FOR_SINGLE_THREAD;
import static com.tradesoft.exchanges.utils.Constants.NO_OF_THREADS;
import static com.tradesoft.exchanges.utils.Utils.subList;

@Component
@Slf4j
public class ExchangeClient {


    @Autowired
    BlockchainClient blockchainClient;

    public ExchangeResponse exchangeOrderBookDetails(ExchangeRequest request) {
        switch (request.getType()) {
            case BLOCKCHAIN:
                BlockchainSymbolsResponse response = blockchainClient.getSymbolsList();
                List<String> symbols = new ArrayList<>(response.getSymbolsDataMap().keySet());

                Map<String, ExchangeResponse> symbolsExchangeResponse;

                if (symbols.size() <= MAX_SYMBOLS_FOR_SINGLE_THREAD) {
                    symbolsExchangeResponse = fetchResponseForSymbols(symbols, request);
                } else {
                    ExecutorService executorService = Executors.newFixedThreadPool(NO_OF_THREADS);
                    CompletionService<Map<String, ExchangeResponse>> executorCompletionService = new ExecutorCompletionService<>(executorService);

                    List<List<String>> symbolsPart = subList(symbols, symbols.size() / NO_OF_THREADS);

                    symbolsPart.stream().map(symbolsSubList -> (Callable<Map<String, ExchangeResponse>>) () -> fetchResponseForSymbols(symbols, request))
                            .forEach(executorCompletionService::submit);

                    symbolsExchangeResponse = IntStream.of(symbolsPart.size()).mapToObj(x -> getExchangeResponseMap(executorCompletionService))
                            .map(Map::entrySet).collect(Collectors.toList())
                            .stream()
                            .flatMap(Set::stream)
                            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

////                    pagination and calculate avg price and quantity
////                    ForkJoinExecutorService, thread size will symbol size-> 150 symbols , 30-40 bids and asks change BlockchainResponse
                }

                // TODO : do something with symbolsExchangeResponse
                // TODO : symbolsExchangeResponse acquired for symbol : ExchangeResponse map

            default:
                throw new IllegalStateException("Unexpected value: " + request.getType());
        }
    }

    private Map<String, ExchangeResponse> getExchangeResponseMap(CompletionService<Map<String, ExchangeResponse>> executorCompletionService) {
        Map<String, ExchangeResponse> resp;
        try {
            resp = executorCompletionService.take().get();
        } catch (InterruptedException | ExecutionException e) {
            log.error("Error while fetching response for thread" + Thread.currentThread().getName());
            resp = new HashMap<>();
        }
        return resp;
    }

    private Map<String, ExchangeResponse> fetchResponseForSymbols(List<String> symbols, ExchangeRequest request) {
        return symbols.stream().collect(Collectors.toMap(Function.identity(), symbol -> ObjectMapper.toBlockchainExchangeResponse(blockchainClient.getResponse(symbol), request)));
    }

}
