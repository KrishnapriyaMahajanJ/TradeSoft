package com.tradesoft.exchanges.dto.request;

import com.tradesoft.exchanges.dto.enums.Exchanges;
import com.tradesoft.exchanges.dto.enums.OrderType;
import com.tradesoft.exchanges.dto.enums.Sorting;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
public class BlockchainExchangeRequest extends ExchangeRequest {

    private String symbol;
    private OrderType orderType;
    private Sorting sorting;

    public BlockchainExchangeRequest(String symbol, OrderType orderType, Sorting sorting) {
        super(Exchanges.BLOCKCHAIN);
        this.symbol = symbol;
        this.orderType = orderType;
        this.sorting = sorting;
    }

    public BlockchainExchangeRequest(String symbol, OrderType orderType,
                                     Sorting sorting, Integer offset, Integer pageSize) {
        super(Exchanges.BLOCKCHAIN);
        this.symbol = symbol;
        this.orderType = orderType;
        this.sorting = sorting;
        this.offset = offset;
        this.pageSize = pageSize;
    }

    public BlockchainExchangeRequest(){
        super(Exchanges.BLOCKCHAIN);
    }
}
