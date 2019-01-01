package com.raju.microservices.currencyconversionservice.proxy;

import com.raju.microservices.currencyconversionservice.bean.CurrencyConversionBean;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//Using Feign with Eureka Server
@FeignClient(name = "currency-exchange-service")
//For load balancing
@RibbonClient(name = "currency-exchange-service")
public interface CurrencyExchangeServiceProxyV2 {
    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    CurrencyConversionBean retrieveExchangeValueV2(@PathVariable String from, @PathVariable String to);
}
