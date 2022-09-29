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

