package com.tradesoft.exchanges.utils;

import com.tradesoft.exchanges.dto.request.ExchangeRequest;
//import com.tradesoft.exchanges.dto.response.clientResponse.Asks;
import com.tradesoft.exchanges.dto.response.BlockchainExchangeResponse;
import com.tradesoft.exchanges.dto.response.ExchangeResponse;
import com.tradesoft.exchanges.dto.response.clientResponse.BlockchainResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ObjectMapper {

    public ExchangeResponse toBlockchainExchangeResponse(BlockchainResponse blockchainResponse, ExchangeRequest request) {
        ExchangeResponse response = null;
        switch (request.getType()) {
            case BLOCKCHAIN: {
                response = BlockchainExchangeResponse.builder()
                        .asks()
                        .bids()
                        .symbol(blockchainResponse.getSymbol())
                        .build();
            }
        }
        return response;
    }
}
