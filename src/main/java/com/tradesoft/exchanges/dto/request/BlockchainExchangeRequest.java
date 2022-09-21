package com.tradesoft.exchanges.dto.request;

import com.tradesoft.exchanges.dto.enums.OrderType;
import com.tradesoft.exchanges.dto.enums.Sorting;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class BlockchainExchangeRequest extends ExchangeRequest {

    private String symbol;
    private OrderType orderType;
    private Sorting sorting;
}
