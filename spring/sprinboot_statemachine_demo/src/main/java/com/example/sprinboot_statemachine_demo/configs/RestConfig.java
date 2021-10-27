package com.example.sprinboot_statemachine_demo.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class RestConfig {

    @Value("${connection_timeout:30000}")
    private String connectionTimeout;

    @Value("${read_timeout:30000}")
    private String readTimeout;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
//        return builder.setConnectTimeout(Duration.ofMillis(Long.parseLong(connectionTimeout)))
//                .setReadTimeout(Duration.ofMillis(Long.parseLong(readTimeout)))
//                .build();
    }
}
