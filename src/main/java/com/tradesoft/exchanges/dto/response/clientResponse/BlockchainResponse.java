package com.tradesoft.exchanges.dto.response.clientResponse;

import com.tradesoft.exchanges.dto.response.Asks;
import com.tradesoft.exchanges.dto.response.Bids;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class BlockchainResponse implements Serializable {

    private String symbol;

    private List<Bids> bids;

    private List<Asks> asks;
}
