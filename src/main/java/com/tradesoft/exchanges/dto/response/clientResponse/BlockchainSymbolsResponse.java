package com.tradesoft.exchanges.dto.response.clientResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class BlockchainSymbolsResponse {

    Map<String, SymbolsData> symbolsDataMap;
}

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
class SymbolsData {
    String base_currency;
    Integer base_currency_scale;
    String counter_currency;
    Integer counter_currency_scale;
    Integer min_price_increment;
    Integer min_price_increment_scale;
    Integer min_order_size;
    Integer min_order_size_scale;
    Integer max_order_size;
    Integer max_order_size_scale;
    Integer lot_size;
    Integer lot_size_scale;
    String status;
    Integer id;
    Integer auction_price;
    Integer auction_size;
    Integer auction_time;
    Integer imbalance;
}
