package com.tradesoft.exchanges.dto.request;

import com.tradesoft.exchanges.model.Exchanges;
import com.tradesoft.exchanges.dto.enums.OrderType;
import com.tradesoft.exchanges.dto.enums.Sorting;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExchangeRequest {

    protected Exchanges type;
    private OrderType orderType;
    private Sorting sorting;

    public ExchangeRequest(Exchanges type, OrderType orderType, Sorting sorting) {
        this.type = type;
        this.orderType = orderType;
        this.sorting = sorting;
    }

    public ExchangeRequest(Exchanges type) {
        this.type = type;
    }
}
