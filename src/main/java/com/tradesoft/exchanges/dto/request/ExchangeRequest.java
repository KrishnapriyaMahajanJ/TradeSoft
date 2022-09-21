package com.tradesoft.exchanges.dto.request;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.tradesoft.exchanges.dto.enums.Exchanges;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = BlockchainExchangeRequest.class, name = "BLOCKCHAIN")
})
@Data
@AllArgsConstructor
@SuperBuilder
@NoArgsConstructor
public abstract class ExchangeRequest {

    protected Exchanges type;
}
