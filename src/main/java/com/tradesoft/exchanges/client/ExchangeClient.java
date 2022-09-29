package com.tradesoft.exchanges.client;

import com.tradesoft.exchanges.dto.enums.Sorting;
import com.tradesoft.exchanges.dto.request.ExchangeRequest;
import com.tradesoft.exchanges.dto.response.BlockchainExchangeResponse;
import com.tradesoft.exchanges.dto.response.ExchangeResponse;
import com.tradesoft.exchanges.dto.response.clientResponse.SymbolsData;
import com.tradesoft.exchanges.utils.Constants;
import com.tradesoft.exchanges.utils.ObjectMapper;
import com.tradesoft.exchanges.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.tradesoft.exchanges.utils.Constants.NO_OF_THREADS;

@Component
public class ExchangeClient {


    private static final CompletionService<List<ExchangeResponse>> executorCompletionService;

    static {
        ExecutorService executorService = Executors.newFixedThreadPool(NO_OF_THREADS);
        executorCompletionService = new ExecutorCompletionService<>(executorService);
    }

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
                    symbolSubLists
                            .parallelStream()
                            .map(symbolsSubList ->
                                    (Callable<List<ExchangeResponse>>) () ->
                                            fetchResponseForSymbols(symbols, request))
                            .forEach(executorCompletionService::submit);
                    symbolsExchangeResponse = IntStream.of(symbolSubLists.size()).mapToObj(x ->
                                    getExchangeResponseMap()).collect(Collectors.toList())
                            .stream().flatMap(List::stream).collect(Collectors.toList());
                }
                return symbolsExchangeResponse;
            default:
                throw new IllegalStateException("Unexpected value: " + request.getType());
        }
    }

    private List<ExchangeResponse> getExchangeResponseMap() {
        List<ExchangeResponse> resp;
        try {
            resp = ExchangeClient.executorCompletionService.take().get();
        } catch (InterruptedException | ExecutionException e) {
            resp = new ArrayList<>();
        }
        return resp;
    }

    private List<ExchangeResponse> fetchResponseForSymbols(List<String> symbols, ExchangeRequest request) {
        List<ExchangeResponse> responses = symbols
                .stream()
                .map(symbol ->
                        ObjectMapper.toBlockchainExchangeResponse(blockchainClient.getResponse(symbol), request))
                .collect(Collectors.toList());

        if (request.getSorting().equals(Sorting.ASCENDING)) {
            return responses.stream()
                    .map(x -> (BlockchainExchangeResponse) (x))
                    .sorted(Comparator.comparing(BlockchainExchangeResponse::getSymbol))
                    .collect(Collectors.toList());
        } else {
            return responses.stream()
                    .map(x -> (BlockchainExchangeResponse) (x))
                    .sorted(Comparator.comparing(BlockchainExchangeResponse::getSymbol).reversed())
                    .collect(Collectors.toList());

        }
    }

}
