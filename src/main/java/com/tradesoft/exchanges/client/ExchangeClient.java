package com.tradesoft.exchanges.client;

import com.tradesoft.exchanges.dto.request.ExchangeRequest;
import com.tradesoft.exchanges.dto.response.ExchangeResponse;
import com.tradesoft.exchanges.dto.response.clientResponse.BlockchainSymbolsResponse;
import com.tradesoft.exchanges.utils.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
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

    public List<ExchangeResponse> exchangeOrderBookDetails(ExchangeRequest request) {
        switch (request.getType()) {
            case BLOCKCHAIN:
                BlockchainSymbolsResponse response = blockchainClient.getSymbolsList();
                List<String> symbols = new ArrayList<>(response.getSymbolsDataMap().keySet());

                List<ExchangeResponse> symbolsExchangeResponse;

                if (symbols.size() <= MAX_SYMBOLS_FOR_SINGLE_THREAD) {
                    symbolsExchangeResponse = fetchResponseForSymbols(symbols, request);
                } else {
                    List<List<String>> symbolSubLists = subList(symbols, MAX_SYMBOLS_FOR_SINGLE_THREAD);

                    symbolsExchangeResponse = symbolSubLists
                            .parallelStream()
                            .map(symbolsSubList -> fetchResponseForSymbols(symbols, request))
                            .collect(Collectors.toList())
                            .stream().flatMap(List::stream).collect(Collectors.toList());

//                    ----------------------------------------------------------------------------

//                    ExecutorService executorService = Executors.newFixedThreadPool(NO_OF_THREADS);
//                    CompletionService<List<ExchangeResponse>> executorCompletionService = new ExecutorCompletionService<>(executorService);
//
//
//                    symbolSubLists
//                            .parallelStream()
//                            .map(symbolsSubList -> (Callable<List<ExchangeResponse>>) () -> fetchResponseForSymbols(symbols, request))
//                            .forEach(executorCompletionService::submit);
//
//                    symbolsExchangeResponse = IntStream.of(symbolSubLists.size()).mapToObj(x ->
//                            getExchangeResponseMap(executorCompletionService)).collect(Collectors.toList())
//                            .stream().flatMap(List::stream).collect(Collectors.toList());
                }
                return symbolsExchangeResponse;
            default:
                throw new IllegalStateException("Unexpected value: " + request.getType());
        }
    }

//    private List<ExchangeResponse> getExchangeResponseMap(CompletionService<List<ExchangeResponse>> executorCompletionService) {
//        List<ExchangeResponse> resp;
//        try {
//            resp = executorCompletionService.take().get();
//        } catch (InterruptedException | ExecutionException e) {
//            log.error("Error while fetching response for thread" + Thread.currentThread().getName());
//            resp = new ArrayList<>();
//        }
//        return resp;
//    }

    private List<ExchangeResponse> fetchResponseForSymbols(List<String> symbols, ExchangeRequest request) {
        return symbols.stream().map(symbol -> ObjectMapper.toBlockchainExchangeResponse(blockchainClient.getResponse(symbol), request)).collect(Collectors.toList());
    }

}
