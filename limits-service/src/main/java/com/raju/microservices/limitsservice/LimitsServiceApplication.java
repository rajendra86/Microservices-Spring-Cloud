package com.raju.microservices.limitsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableHystrix
public class LimitsServiceApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(LimitsServiceApplication.class, args);
        for (String bean : applicationContext.getBeanDefinitionNames()) {
           // System.out.println("Bean Name => " + bean);

        }
    }
}

