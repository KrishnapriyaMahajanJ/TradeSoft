package com.tradesoft.exchanges.service;

import com.tradesoft.exchanges.model.Exchanges;
import com.tradesoft.exchanges.model.ExchangeMetadata;
import com.tradesoft.exchanges.dto.request.ExchangeRequest;
import com.tradesoft.exchanges.dto.response.ExchangeResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ExchangeDataService {

    List<ExchangeResponse> getOrderBook(ExchangeRequest request);

    void uploadMetadata(MultipartFile file, Exchanges exchanges) throws IOException;

    ExchangeMetadata getMetadata(Exchanges exchange);
}
