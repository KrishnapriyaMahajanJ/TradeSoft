package com.tradesoft.exchanges.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Asks {
    private Double px;
    private Double qty;
    private Long num;
}
