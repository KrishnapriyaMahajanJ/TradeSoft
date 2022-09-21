package com.tradesoft.exchanges.dto.response;

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
        @JsonSubTypes.Type(value = BlockchainExchangeResponse.class, name = "BLOCKCHAIN")
})
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public abstract class ExchangeResponse {

    protected Exchanges type;

}
