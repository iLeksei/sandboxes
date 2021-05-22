package com.example.hibernate_demo.configs;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class JdbcConfig {

//    @Bean
//    public DataSource getDataSource() {
//        return DataSourceBuilder.create()
//                .driverClassName("org.hibernate.dialect.OracleDialect")
//                .url("127.0.0.1:1521/XE")
//                .username("JPA_USR")
//                .password("123456")
//                .build();
//    }
//

}
