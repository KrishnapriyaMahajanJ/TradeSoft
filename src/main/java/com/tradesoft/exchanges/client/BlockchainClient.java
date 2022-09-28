package com.tradesoft.exchanges.client;

import com.tradesoft.exchanges.dto.response.clientResponse.BlockchainResponse;
import com.tradesoft.exchanges.dto.response.clientResponse.BlockchainSymbolsResponse;
import com.tradesoft.exchanges.dto.response.clientResponse.SymbolsData;
import com.tradesoft.exchanges.exceptions.ExchangeRequestResponseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
public class BlockchainClient {

    @Value("${blockchain.baseUrl}")
    private String baseUrl;

    @Value("${blockchain.api.token}")
    private String apiToken;

    public BlockchainResponse getResponse(String symbol) {
        RestTemplate restTemplate = new RestTemplate();
        String ORDER_BOOK_DATA = "/l3/";
        System.out.println(baseUrl+ORDER_BOOK_DATA+symbol);
        ResponseEntity<BlockchainResponse> response
                = restTemplate.getForEntity(baseUrl + ORDER_BOOK_DATA + symbol, BlockchainResponse.class);

        if(!response.getStatusCode().is2xxSuccessful()){
            System.out.println("error "+ response.getBody());
            throw new ExchangeRequestResponseException("error in client request response "+response.getStatusCode());
        }
        return response.getBody();
    }


    public Map<String, SymbolsData> getSymbolsList() {
        RestTemplate restTemplate = new RestTemplate();
        String SYMBOLS = "/symbols";
        System.out.println(baseUrl+SYMBOLS);
        ResponseEntity<Map<String, SymbolsData>> response =  restTemplate.exchange(baseUrl  + SYMBOLS,
                HttpMethod.GET, null, new ParameterizedTypeReference<Map<String, SymbolsData>>() {
                });
        //        if(!response.getStatusCode().is2xxSuccessful()){
//            System.out.println("error "+ response.getBody());
//            throw new ExchangeRequestResponseException("error in client request response "+response.getStatusCode());
//        }
        return response.getBody();
    }
}
