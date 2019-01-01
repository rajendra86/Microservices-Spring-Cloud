package com.raju.microservices.currencyexchangeservice.repository;

import com.raju.microservices.currencyexchangeservice.bean.ExchangeValue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyExchangeRepository extends JpaRepository<ExchangeValue, Long> {
     ExchangeValue findByFromAndTo(String from, String to);

}
