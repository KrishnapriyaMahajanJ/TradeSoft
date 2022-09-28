package com.tradesoft.exchanges.utils;

import com.tradesoft.exchanges.dto.request.ExchangeRequest;
import com.tradesoft.exchanges.dto.response.BlockchainExchangeResponse;
import com.tradesoft.exchanges.dto.response.ExchangeResponse;
import com.tradesoft.exchanges.dto.response.clientResponse.AverageResponse;
import com.tradesoft.exchanges.dto.response.clientResponse.BlockchainResponse;
import com.tradesoft.exchanges.dto.response.clientResponse.Order;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class ObjectMapper {

    public ExchangeResponse toBlockchainExchangeResponse(BlockchainResponse blockchainResponse, ExchangeRequest request) {
        ExchangeResponse response = null;
        switch (request.getType()) {
            case BLOCKCHAIN: {
                response = BlockchainExchangeResponse.builder()
                        .bids(transformOrder(blockchainResponse.getBids()))
                        .asks(transformOrder(blockchainResponse.getAsks()))
                        .symbol(blockchainResponse.getSymbol())
                        .build();
            }
        }
        return response;
    }

    private AverageResponse transformOrder(List<Order> orders) {
        return AverageResponse.builder()
                .avgPrice(orders.stream()
                        .map(Order::getPx)
                        .mapToDouble(x -> x)
                        .average().orElse(0.0))
                .quantity(orders.stream()
                        .map(Order::getQty)
                        .mapToDouble(x -> x).sum())
                .build();
    }
}
