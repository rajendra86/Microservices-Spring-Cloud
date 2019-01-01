package com.raju.microservices.currencyconversionservice;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.raju.microservices.currencyconversionservice.bean.CurrencyConversionBean;
import com.raju.microservices.currencyconversionservice.proxy.CurrencyExchangeServiceProxy;
import com.raju.microservices.currencyconversionservice.proxy.CurrencyExchangeServiceProxyV2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CurrencyConversionController {
    private static final Logger logger = LoggerFactory.getLogger(CurrencyConversionController.class);

    @Autowired
    private CurrencyExchangeServiceProxy exchangeServiceProxy;

    @Autowired
    private CurrencyExchangeServiceProxyV2 exchangeServiceProxyV2;

    @Autowired
    private EurekaClient eurekaClient;

    @Autowired
    @Qualifier("loadBalancedRestTemplate")
    private RestTemplate restTemplate;

    @GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean convertCurrency(@PathVariable String from,
                                                 @PathVariable String to,
                                                 @PathVariable BigDecimal quantity) {
        logger.info("CurrencyConversion - RestTemplate method");
        CurrencyConversionBean currencyConversionBean = null;
        try {
            Application application = eurekaClient.getApplication("currency-exchange-service");
            InstanceInfo instanceInfo = application.getInstances().get(0);
            for(InstanceInfo instance : application.getInstances()){
                logger.info("Application Name=> " + instance.getAppName());
                logger.info("Application Id=> " + instance.getId());
                logger.info("Application Host Name=> " + instance.getHostName());
                logger.info("Application Port=> " + instance.getPort());
            }

            String uri = "http://currency-exchange-service/currency-exchange/from/{from}/to/{to}";
            Map<String, String> uriVariables = new HashMap<>();
            uriVariables.put("from", from);
            uriVariables.put("to", to);

            ResponseEntity<CurrencyConversionBean> responseEntity =
                    restTemplate.getForEntity
                            (uri, CurrencyConversionBean.class, uriVariables);
            currencyConversionBean = responseEntity.getBody();
        } catch (Exception ex) {
            logger.error("Exception ", ex);
        }

        return new CurrencyConversionBean(currencyConversionBean.getId(), from, to,
                currencyConversionBean.getConversionMultiple(), quantity,
                quantity.multiply(currencyConversionBean.getConversionMultiple()),
                currencyConversionBean.getPort(), currencyConversionBean.getLimitsVO());
    }

    @GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean convertCurrencyFeign(@PathVariable String from,
                                                  @PathVariable String to,
                                                  @PathVariable BigDecimal quantity) {

        CurrencyConversionBean currencyConversionBean = exchangeServiceProxy.retrieveExchangeValue(from,to);
        logger.info("CurrencyConversionController=> " + currencyConversionBean);

        return new CurrencyConversionBean(currencyConversionBean.getId(), from, to,
                currencyConversionBean.getConversionMultiple(), quantity,
                quantity.multiply(currencyConversionBean.getConversionMultiple()),
                currencyConversionBean.getPort(), currencyConversionBean.getLimitsVO());
    }

    @GetMapping("/currency-converter-feign-without-zuul/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean convertCurrencyFeignWithOutZuul(@PathVariable String from,
                                                       @PathVariable String to,
                                                       @PathVariable BigDecimal quantity) {
        CurrencyConversionBean currencyConversionBean = null;
        try {
            currencyConversionBean = exchangeServiceProxyV2.retrieveExchangeValueV2(from,to);
            logger.info("CurrencyConversionController::convertCurrencyFeignWithOutZuul=> " + currencyConversionBean);
        }catch (Exception ex) {
            logger.error("CurrencyConversionController::convertCurrencyFeignWithOutZuul::Exception=> ", ex);
        }

        return new CurrencyConversionBean(currencyConversionBean.getId(), from, to,
                currencyConversionBean.getConversionMultiple(), quantity,
                quantity.multiply(currencyConversionBean.getConversionMultiple()),
                currencyConversionBean.getPort(), currencyConversionBean.getLimitsVO());
    }
}