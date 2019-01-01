package com.raju.microservices.limitsservice;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.raju.microservices.limitsservice.bean.LimitConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsConfigurationController {

//    @Value("${limits-service.minimum}")
//    private int minimum;
//    @Value("${limits-service.maximum}")
//    private int maximum;

    @Autowired
    private Configuration configuration;

    @GetMapping("/limits")
    public LimitConfiguration retrieveLimitsFromConfigurations () {
        return new LimitConfiguration(configuration.getMaximum(), configuration.getMinimum());
    }

    @GetMapping("/limits-hystrix")
    @HystrixCommand(fallbackMethod = "fallbackDemoHystrix")
    public LimitConfiguration demoHystrix () {
        //throw new RuntimeException("Service Not available");
        return null;
    }

    public LimitConfiguration fallbackDemoHystrix () {
        return new LimitConfiguration(9,0);
    }
}
