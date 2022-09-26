package com.tradesoft.exchanges.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.tradesoft.exchanges.dto.request.BlockchainMetadata;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({@JsonSubTypes.Type(value = BlockchainMetadata.class, name = "BLOCKCHAIN")})
@Data
@NoArgsConstructor
public abstract class ExchangeMetadata {

    @Id
    protected Exchanges type;

    public ExchangeMetadata(Exchanges type) {
        this.type = type;
    }
}