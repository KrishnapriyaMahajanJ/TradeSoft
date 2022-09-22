package com.tradesoft.exchanges.controller;

import com.tradesoft.exchanges.dto.enums.Exchanges;
import com.tradesoft.exchanges.dto.request.ExchangeRequest;
import com.tradesoft.exchanges.service.ExchangeDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/v1")
public class TradeSoftController {

    @Autowired
    ExchangeDataService exchangeDataService;

    @PostMapping("/exchanges/order-books")
    ResponseEntity getOrderBook(@RequestBody ExchangeRequest exchangeRequest) {
        try {
            return ResponseEntity.ok(exchangeDataService.getOrderBook(exchangeRequest));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping(value = "exchange/{exchange-name}/metadata", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity uploadMetadata(@RequestPart("file") MultipartFile file, @PathVariable("exchange-name") Exchanges exchanges) {
        try {
            exchangeDataService.uploadMetadata(file, exchanges);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }

    }

    @GetMapping(value = "exchange/{exchange-name}/metadata")
    ResponseEntity getMetadata( @PathVariable("exchange-name") Exchanges exchange) {
        try {
            return ResponseEntity.ok(exchangeDataService.getMetadata(exchange));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }

    }
}
