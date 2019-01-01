package com.raju.microservices.currencyexchangeservice;

import com.raju.microservices.currencyexchangeservice.bean.ExchangeValue;
import com.raju.microservices.currencyexchangeservice.bean.LimitsVO;
import com.raju.microservices.currencyexchangeservice.repository.CurrencyExchangeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@RestController
public class CurrencyExchangeController {

    private static final Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);
    @Autowired
    private Environment environment;

    @Autowired
    private CurrencyExchangeRepository currencyExchangeRepository;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {

        ExchangeValue exchangeValue
                = currencyExchangeRepository.findByFromAndTo(from, to);
        exchangeValue.setPort(Integer.parseInt(environment.getProperty("server.port")));

        //Calling Limits_service to fetch max and min
        ResponseEntity<LimitsVO> responseEntity = new RestTemplate()
                .getForEntity("http://localhost:8080/limits", LimitsVO.class);
        exchangeValue.setLimitsVO(responseEntity.getBody());

        logger.info("CurrenyExchangeController=> " + exchangeValue);
        return exchangeValue;
    }
}
