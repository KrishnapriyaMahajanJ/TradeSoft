package com.tradesoft.exchanges.dto.request;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.tradesoft.exchanges.dto.enums.Exchanges;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({@JsonSubTypes.Type(value = BlockchainExchangeRequest.class, name = "BLOCKCHAIN")})
@Data
@NoArgsConstructor
public abstract class ExchangeRequest {

    protected Exchanges type;
    protected Integer offset;
    protected Integer pageSize;

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
