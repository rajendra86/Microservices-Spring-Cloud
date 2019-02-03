package com.raju.microservices.springsecurityoauth.config.jdbc;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

/**
 *
 */
@Configuration
public class DataBaseConfig {

    private static final Logger logger = LoggerFactory.getLogger(DataBaseConfig.class);

    @Autowired
    Environment env;

    @Bean
    public DataSource dataSource() {
        MysqlConnectionPoolDataSource dataSource = null;
        try {
            dataSource = new MysqlConnectionPoolDataSource();
            dataSource.setURL(env.getRequiredProperty("spring.datasource.url"));
            dataSource.setUser(env.getRequiredProperty("spring.datasource.username"));
            dataSource.setPassword(env.getRequiredProperty("spring.datasource.password"));
        } catch (Exception ex) {
            logger.error("Exception=> ", ex);
        }
        return dataSource;
    }
}
