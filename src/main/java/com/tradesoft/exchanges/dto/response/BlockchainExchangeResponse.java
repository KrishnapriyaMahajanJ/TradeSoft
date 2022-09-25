package com.tradesoft.exchanges.dto.response;

import com.tradesoft.exchanges.dto.enums.Exchanges;
import com.tradesoft.exchanges.dto.response.clientResponse.Asks;
import com.tradesoft.exchanges.dto.response.clientResponse.Bids;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BlockchainExchangeResponse extends ExchangeResponse {

    private String symbol;

    private List<Bids> bids;

    private List<Asks> asks;

    BlockchainExchangeResponse(){
        super(Exchanges.BLOCKCHAIN);
    }

}
