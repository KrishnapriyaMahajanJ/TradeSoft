package com.tradesoft.exchanges.dto.response.clientResponse;

import com.tradesoft.exchanges.dto.enums.Exchanges;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class BlockchainResponse implements Serializable {

    private Exchanges exchange;

    private String symbol;

    private AverageResponse bids;

    private AverageResponse asks;
}

class AverageResponse{
    Double avgPrice;
    Double quantity;
    private Long num;

}