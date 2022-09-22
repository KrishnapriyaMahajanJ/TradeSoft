package com.tradesoft.exchanges;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@SpringBootApplication
@EnableWebMvc
@EnableMongoRepositories(basePackages = "com.tradesoft.exchanges")
public class ExchangesServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExchangesServicesApplication.class, args);
	}
}
