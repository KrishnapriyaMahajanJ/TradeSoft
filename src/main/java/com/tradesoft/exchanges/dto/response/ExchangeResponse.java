package com.tradesoft.exchanges.dto.response;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.tradesoft.exchanges.model.Exchanges;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        property = "exchange")
@JsonSubTypes({
        @JsonSubTypes.Type(value = BlockchainExchangeResponse.class, name = "BLOCKCHAIN")
})
@Data
@NoArgsConstructor
@SuperBuilder
public abstract class ExchangeResponse {

    protected Exchanges exchange;

    public ExchangeResponse(Exchanges type) {
        this.exchange = type;
    }

}
