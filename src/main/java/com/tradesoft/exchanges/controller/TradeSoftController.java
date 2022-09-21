package com.tradesoft.exchanges.controller;

import com.tradesoft.exchanges.client.BlockchainClient;
import com.tradesoft.exchanges.dto.request.BlockchainExchangeRequest;
import com.tradesoft.exchanges.dto.request.ExchangeRequest;
import com.tradesoft.exchanges.dto.response.clientResponse.BlockchainResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class TradeSoftController {


    @Autowired
    BlockchainClient blockchainClient;

    @PostMapping("/details")
    ResponseEntity<BlockchainResponse> getDetails(@RequestBody ExchangeRequest exchangeRequest) {
        return ResponseEntity.ok(blockchainClient.getResponse(((BlockchainExchangeRequest)exchangeRequest).getSymbol()));
    }
}
