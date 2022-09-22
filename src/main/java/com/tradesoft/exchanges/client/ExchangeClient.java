package com.tradesoft.exchanges.client;

import com.tradesoft.exchanges.dto.request.BlockchainExchangeRequest;
import com.tradesoft.exchanges.dto.request.ExchangeRequest;
import com.tradesoft.exchanges.dto.response.ExchangeResponse;
import com.tradesoft.exchanges.dto.response.clientResponse.BlockchainResponse;
import com.tradesoft.exchanges.exceptions.NotValidExchange;
import com.tradesoft.exchanges.utils.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExchangeClient {

    @Autowired
    BlockchainClient blockchainClient;

    public ExchangeResponse exchangeOrderBookDetails(ExchangeRequest request) {
        switch (request.getType()) {
            case BLOCKCHAIN:
                BlockchainResponse blockchainResponse = blockchainClient.getResponse(((BlockchainExchangeRequest) request).getSymbol());
                return ObjectMapper.toBlockchainExchangeResponse(blockchainResponse, request);
        }
        throw new NotValidExchange("Invalid Exchange Name");
    }

}
