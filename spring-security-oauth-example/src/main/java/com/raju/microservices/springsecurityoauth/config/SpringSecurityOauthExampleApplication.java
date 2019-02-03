package com.raju.microservices.springsecurityoauth.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value = "com.raju.microservices.springsecurityoauth.*")
public class SpringSecurityOauthExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityOauthExampleApplication.class, args);
    }

}

