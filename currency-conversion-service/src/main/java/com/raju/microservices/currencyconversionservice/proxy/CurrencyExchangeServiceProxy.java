package com.raju.microservices.currencyconversionservice.proxy;

import com.raju.microservices.currencyconversionservice.bean.CurrencyConversionBean;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//Uncomment below if not using Zuul api gateway and not using Ribbon
//@FeignClient(name = "currency-exchange-service", url = "localhost:8000")

//Uncomment below if not using Zuul api gateway and using Ribbon
//@FeignClient(name = "currency-exchange-service")

//Using Zuul Api gateway
@FeignClient(name = "netflix-zuul-api-gateway-server")

//Uncomment when not using Zuul api gateway and using Ribbon with Eureka Server
//@RibbonClient(name = "currency-exchange-service")
public interface CurrencyExchangeServiceProxy {

    //Uncomment below if not using Zuul api gateway
    //@GetMapping("/currency-exchange/from/{from}/to/{to}")
    @GetMapping("/currency-exchange-service/currency-exchange/from/{from}/to/{to}")
    CurrencyConversionBean retrieveExchangeValue(@PathVariable String from, @PathVariable String to);
}
