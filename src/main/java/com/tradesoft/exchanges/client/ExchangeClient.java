package com.tradesoft.exchanges.client;

import com.tradesoft.exchanges.dto.request.ExchangeRequest;
import com.tradesoft.exchanges.dto.response.ExchangeResponse;
import com.tradesoft.exchanges.dto.response.clientResponse.SymbolsData;
import com.tradesoft.exchanges.utils.Constants;
import com.tradesoft.exchanges.utils.ObjectMapper;
import com.tradesoft.exchanges.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.tradesoft.exchanges.utils.Constants.NO_OF_THREADS;

@Component
public class ExchangeClient {


    @Autowired
    BlockchainClient blockchainClient;

    public List<ExchangeResponse> exchangeOrderBookDetails(ExchangeRequest request) {
        switch (request.getType()) {
            case BLOCKCHAIN:
                Map<String, SymbolsData> response = blockchainClient.getSymbolsList();
                List<String> symbols = new ArrayList<>(response.keySet());
                List<ExchangeResponse> symbolsExchangeResponse;
                if (symbols.size() <= Constants.MAX_SYMBOLS_FOR_SINGLE_THREAD) {
                    symbolsExchangeResponse = fetchResponseForSymbols(symbols, request);
                } else {
                    List<List<String>> symbolSubLists = Utils.subList(symbols, Constants.MAX_SYMBOLS_FOR_SINGLE_THREAD);
                    long milli = System.currentTimeMillis();
                    ExecutorService executorService = Executors.newFixedThreadPool(NO_OF_THREADS);
                    CompletionService<List<ExchangeResponse>> executorCompletionService = new ExecutorCompletionService<>(executorService);
                    symbolSubLists
                            .parallelStream()
                            .map(symbolsSubList -> (Callable<List<ExchangeResponse>>) () -> fetchResponseForSymbols(symbols, request))
                            .forEach(executorCompletionService::submit);
                    symbolsExchangeResponse = IntStream.of(symbolSubLists.size()).mapToObj(x ->
                                    getExchangeResponseMap(executorCompletionService)).collect(Collectors.toList())
                            .stream().flatMap(List::stream).collect(Collectors.toList());
                    long milliend = System.currentTimeMillis();
                    System.out.println("Kitna time laga " + (milliend - milli));
                }
                return symbolsExchangeResponse;
            default:
                throw new IllegalStateException("Unexpected value: " + request.getType());
        }
    }

    private List<ExchangeResponse> getExchangeResponseMap(CompletionService<List<ExchangeResponse>> executorCompletionService) {
        List<ExchangeResponse> resp;
        try {
            resp = executorCompletionService.take().get();
        } catch (InterruptedException | ExecutionException e) {
            resp = new ArrayList<>();
        }
        return resp;
    }

    private List<ExchangeResponse> fetchResponseForSymbols(List<String> symbols, ExchangeRequest request) {
        return symbols.stream().map(symbol -> ObjectMapper.toBlockchainExchangeResponse(blockchainClient.getResponse(symbol),
                request)).collect(Collectors.toList());
    }

}
