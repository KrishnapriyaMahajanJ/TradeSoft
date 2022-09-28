package com.tradesoft.exchanges.dto.response.clientResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class SymbolsData {
    String base_currency;
    Long base_currency_scale;
    String counter_currency;
    Long counter_currency_scale;
    Long min_price_increment;
    Long min_price_increment_scale;
    Long min_order_size;
    Long min_order_size_scale;
    Long max_order_size;
    Long max_order_size_scale;
    Long lot_size;
    Long lot_size_scale;
    String status;
    Long id;
    Long auction_price;
    Long auction_size;
    Long auction_time;
    Long imbalance;
}
