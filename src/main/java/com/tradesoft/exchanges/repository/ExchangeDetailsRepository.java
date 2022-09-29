package com.tradesoft.exchanges.repository;

import com.tradesoft.exchanges.model.Exchanges;
import com.tradesoft.exchanges.model.ExchangeMetadata;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeDetailsRepository extends MongoRepository<ExchangeMetadata, Exchanges> {
}
