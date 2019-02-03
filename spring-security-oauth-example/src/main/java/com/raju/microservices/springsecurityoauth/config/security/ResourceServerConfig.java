package com.raju.microservices.springsecurityoauth.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

/**
 *
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{

    private static final String RESOURCE_ID = "rest_api";

    @Autowired
    OAuth2AccessDeniedHandler oauthAccessDeniedHandler;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(RESOURCE_ID);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
//        http.anonymous().disable()
//                .requestMatchers().antMatchers("/api/security/**").and().authorizeRequests().anyRequest()
//                .access("hasRole('ADMIN')").and().exceptionHandling()
//                .accessDeniedHandler(oauthAccessDeniedHandler);

        http.anonymous().disable()
                .authorizeRequests().antMatchers("/api/admin/**").access("hasRole('ADMIN')")
                .and().authorizeRequests().antMatchers("/api/user/**").access("hasRole('USER')")
                .and().exceptionHandling().accessDeniedHandler(oauthAccessDeniedHandler);
    }
}
