package com.tradesoft.exchanges.dto.response;

import com.tradesoft.exchanges.model.Exchanges;
import com.tradesoft.exchanges.dto.response.clientResponse.AverageResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class BlockchainExchangeResponse extends ExchangeResponse {

    private String symbol;

    private AverageResponse bids;

    private AverageResponse asks;

    BlockchainExchangeResponse() {
        super(Exchanges.BLOCKCHAIN);
    }

}
