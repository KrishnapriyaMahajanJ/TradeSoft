package com.tradesoft.exchanges.dto.response;

import com.tradesoft.exchanges.dto.enums.OrderType;
import com.tradesoft.exchanges.model.Exchanges;
import com.tradesoft.exchanges.dto.response.clientResponse.AverageResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BlockchainExchangeResponse extends ExchangeResponse {

    private String symbol;

    private AverageResponse orderDetails;

    OrderType orderType;


    BlockchainExchangeResponse() {
        super(Exchanges.BLOCKCHAIN);
    }

    public BlockchainExchangeResponse(String symbol, AverageResponse orderDetails, OrderType orderType) {
        super(Exchanges.BLOCKCHAIN);
        this.symbol = symbol;
        this.orderDetails = orderDetails;
        this.orderType = orderType;
    }
}
