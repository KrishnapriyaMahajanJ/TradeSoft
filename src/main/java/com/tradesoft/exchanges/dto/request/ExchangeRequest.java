package com.tradesoft.exchanges.dto.request;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.tradesoft.exchanges.dto.enums.Exchanges;
import com.tradesoft.exchanges.dto.enums.OrderType;
import com.tradesoft.exchanges.dto.enums.Sorting;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExchangeRequest {

    protected Exchanges type;
    protected Integer offset;
    protected Integer pageSize;
    private OrderType orderType;

    private Sorting sorting;

    public ExchangeRequest(Exchanges type, Integer offset, Integer pageSize, OrderType orderType, Sorting sorting) {
        this.type = type;
        this.offset = offset;
        this.pageSize = pageSize;
        this.orderType = orderType;
        this.sorting = sorting;
    }

    public ExchangeRequest(Exchanges type, Integer offset, Integer pageSize) {
        this.type = type;
        this.offset = offset != null ? offset : 0;
        this.pageSize = pageSize != null ? pageSize : Integer.MAX_VALUE;
    }

    public ExchangeRequest(Exchanges type) {
        this.type = type;
        this.offset = 0;
        this.pageSize = Integer.MAX_VALUE;
    }
}
