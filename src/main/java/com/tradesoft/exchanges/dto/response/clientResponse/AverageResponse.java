package com.tradesoft.exchanges.dto.response.clientResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AverageResponse {
    private Double avgPrice;
    private Double quantity;
//    private Long num;

    @Builder
    public AverageResponse(Double avgPrice, Double quantity){
        this.setAvgPrice(avgPrice);
        this.setQuantity(quantity);
//        this.setNum(num);
    }

}
