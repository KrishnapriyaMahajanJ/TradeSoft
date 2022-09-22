package com.tradesoft.exchanges.dto.request;

import com.tradesoft.exchanges.dto.enums.Exchanges;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BlockchainMetadata extends ExchangeMetadata {

    private String name;
    private String description;
    private String webpage;
    private String address;

    public BlockchainMetadata(String name, String description, String webpage, String address) {
        super(Exchanges.BLOCKCHAIN);
        this.name = name;
        this.description = description;
        this.webpage = webpage;
        this.address = address;
    }
}
