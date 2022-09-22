package com.tradesoft.exchanges.repository;

import com.tradesoft.exchanges.dto.enums.Exchanges;
import com.tradesoft.exchanges.dto.request.ExchangeMetadata;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeDetailsRepository extends MongoRepository<ExchangeMetadata, Exchanges> {
}
