package com.raju.microservices.currencyconversionservice;

import brave.sampler.Sampler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableFeignClients("com.raju.microservices.currencyconversionservice.proxy")
@EnableDiscoveryClient
public class CurrencyConversionServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CurrencyConversionServiceApplication.class, args);
    }

    @Bean
    public Sampler defaultSampler() {
        return Sampler.ALWAYS_SAMPLE;
    }

    @LoadBalanced
    @Bean(name = "loadBalancedRestTemplate")
    RestTemplate loadBalancedRestTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.build();
    }

    @Bean(name = "simpleRestTemplate")
    RestTemplate simpleRestTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.build();
    }
}

