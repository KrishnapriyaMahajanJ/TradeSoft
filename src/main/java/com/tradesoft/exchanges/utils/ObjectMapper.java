package com.tradesoft.exchanges.utils;

import com.tradesoft.exchanges.dto.request.ExchangeRequest;
import com.tradesoft.exchanges.dto.response.BlockchainExchangeResponse;
import com.tradesoft.exchanges.dto.response.ExchangeResponse;
import com.tradesoft.exchanges.dto.response.clientResponse.BlockchainResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ObjectMapper {

    public ExchangeResponse toBlockchainExchangeResponse(BlockchainResponse blockchainResponse, ExchangeRequest request) {
        ExchangeResponse response = null;
        int limit = (request.getOffset() + 1) * request.getPageSize();
        switch (request.getType()) {
            case BLOCKCHAIN: {
                response = BlockchainExchangeResponse.builder()
                        .asks(blockchainResponse.getAsks().subList(request.getOffset(), limit))
                        .bids(blockchainResponse.getBids().subList(request.getOffset(), limit))
                        .symbol(blockchainResponse.getSymbol())
                        .build();
            }
        }
        return response;
    }
}
