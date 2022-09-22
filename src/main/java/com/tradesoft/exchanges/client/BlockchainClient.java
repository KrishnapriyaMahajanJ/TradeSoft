package com.tradesoft.exchanges.client;

import com.tradesoft.exchanges.dto.response.clientResponse.BlockchainResponse;
import com.tradesoft.exchanges.exceptions.ExchangeRequestResponseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class BlockchainClient {

    @Value("${blockchain.baseUrl}")
    private String baseUrl;

    @Value("${blockchain.api.token}")
    private String apiToken;

    public BlockchainResponse getResponse(String symbol) {
        RestTemplate restTemplate = new RestTemplate();
        String ORDER_BOOK_DATA = "/l3/";
        ResponseEntity<BlockchainResponse> response
                = restTemplate.getForEntity(baseUrl + ORDER_BOOK_DATA + symbol, BlockchainResponse.class);

        if(!response.getStatusCode().is2xxSuccessful()){
            throw new ExchangeRequestResponseException("error in client request response "+response.getStatusCode());
        }
        return response.getBody();
    }
}
