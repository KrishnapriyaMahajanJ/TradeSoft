package com.tradesoft.exchanges.dto.response;

import com.tradesoft.exchanges.dto.enums.Exchanges;
import com.tradesoft.exchanges.dto.enums.OrderType;
import com.tradesoft.exchanges.dto.enums.Sorting;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
public class BlockchainExchangeResponse extends ExchangeResponse {

    private String symbol;

    private List<Bids> bids;

    private List<Asks> asks;

    public BlockchainExchangeResponse(String symbol, List<Bids> bids, List<Asks> asks) {
        super(Exchanges.BLOCKCHAIN);
        this.symbol = symbol;
        this.bids = bids;
        this.asks = asks;
    }

    public BlockchainExchangeResponse(){
        super(Exchanges.BLOCKCHAIN);
    }

}
