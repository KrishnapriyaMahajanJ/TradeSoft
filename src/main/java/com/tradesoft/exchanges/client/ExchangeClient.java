package com.tradesoft.exchanges.client;

import com.tradesoft.exchanges.dto.request.ExchangeRequest;
import com.tradesoft.exchanges.dto.response.ExchangeResponse;
import com.tradesoft.exchanges.dto.response.clientResponse.BlockchainResponse;
import com.tradesoft.exchanges.dto.response.clientResponse.BlockchainSymbolsResponse;
import com.tradesoft.exchanges.exceptions.NotValidExchange;
import com.tradesoft.exchanges.utils.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;

@Component
public class ExchangeClient {

    @Autowired
    BlockchainClient blockchainClient;

    public ExchangeResponse exchangeOrderBookDetails(ExchangeRequest request) {
        switch (request.getType()) {
            case BLOCKCHAIN:
                BlockchainSymbolsResponse response = blockchainClient.getSymbolsList();
                Set<String> symbols = response.getSymbolsDataMap().keySet();
                BlockchainResponse blockchainResponse= null;
                for(String symbol :  symbols) {
                    int limit = (request.getOffset() + 1) * request.getPageSize();

                    // paginination and claculate avg price and quantity
//                    ForkJoinExecutorService, thread size will symbol size-> 150 symbols , 30-40 bids and asks change BlockchainResponse
                     blockchainResponse = blockchainClient.getResponse(symbol);
                }
                return ObjectMapper.toBlockchainExchangeResponse(blockchainResponse, request);
            default:
                throw new IllegalStateException("Unexpected value: " + request.getType());
        }
    }

}
