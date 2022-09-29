package com.tradesoft.exchanges.service.impl;

import com.tradesoft.exchanges.client.ExchangeClient;
import com.tradesoft.exchanges.model.Exchanges;
import com.tradesoft.exchanges.dto.request.BlockchainMetadata;
import com.tradesoft.exchanges.model.ExchangeMetadata;
import com.tradesoft.exchanges.dto.request.ExchangeRequest;
import com.tradesoft.exchanges.dto.response.ExchangeResponse;
import com.tradesoft.exchanges.exceptions.DataNotAvailable;
import com.tradesoft.exchanges.exceptions.NotValidExchange;
import com.tradesoft.exchanges.repository.ExchangeDetailsRepository;
import com.tradesoft.exchanges.service.ExchangeDataService;
import com.tradesoft.exchanges.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;


@Service
public class ExchangeDataServiceImpl implements ExchangeDataService {

    @Autowired
    ExchangeDetailsRepository exchangeDetailsRepository;
    @Autowired
    ExchangeClient exchangeClient;

    @Override
    public List<ExchangeResponse> getOrderBook(ExchangeRequest request) {
        return exchangeClient.exchangeOrderBookDetails(request);
    }

    @Override
    public void uploadMetadata(MultipartFile file, Exchanges exchanges) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream(),
                StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(Constants.COMMA);
                ExchangeMetadata metadata;
                switch (exchanges) {
                    case BLOCKCHAIN:
                        metadata = BlockchainMetadata.builder()
                                .address(values[2])
                                .description(values[1])
                                .name(values[0])
                                .build();
                        break;
                    default:
                        throw new NotValidExchange("Invalid Exchanges");
                }
                exchangeDetailsRepository.save(metadata);
            }
        }
    }

    @Override
    public ExchangeMetadata getMetadata(Exchanges exchange) {
        return exchangeDetailsRepository.findById(exchange).orElseThrow(DataNotAvailable::new);
    }
}
