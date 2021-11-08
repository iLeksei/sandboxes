package com.example.ssl_context_demo.configs;

import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;

@Configuration
public class RestConfig {

    private String pass = "123456";

    @Bean(name = "restSecured")
    public RestTemplate restSecured(RestTemplateBuilder builder) throws Exception {
        SSLContext sslContext = SSLContextBuilder
                .create()
                .loadKeyMaterial(ResourceUtils.getFile("classpath:ssl/server_home_keystore.jks"), pass.toCharArray(), pass.toCharArray())
                .loadTrustMaterial(ResourceUtils.getFile("classpath:ssl/server_home_keystore.jks"), pass.toCharArray())
                .build();

        CloseableHttpClient client = HttpClients.custom()
                .setSSLContext(sslContext)
                .build();

        return builder
                .requestFactory(() -> new HttpComponentsClientHttpRequestFactory(client))
                .build();
    }
}
