package com.tradesoft.exchanges.utils;

import com.tradesoft.exchanges.dto.enums.OrderType;
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
                if (request.getOrderType().equals(OrderType.ASK))
                    response = BlockchainExchangeResponse.builder()
                            .orderDetails(transformOrder(blockchainResponse.getAsks()))
                            .orderType(OrderType.ASK)
                            .symbol(blockchainResponse.getSymbol())
                            .build();
                else
                    response = BlockchainExchangeResponse.builder()
                            .orderDetails(transformOrder(blockchainResponse.getBids()))
                            .orderType(OrderType.BID)
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
