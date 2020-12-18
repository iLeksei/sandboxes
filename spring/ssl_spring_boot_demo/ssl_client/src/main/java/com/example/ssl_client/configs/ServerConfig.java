package com.example.ssl_client.configs;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import sun.security.util.HostnameChecker;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocketFactory;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

@Configuration
public class ServerConfig {

    @Value("${trust.store}")
    private Resource trustStore;

    @Bean
    RestTemplate getRestTemplate() throws Exception {
//        SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(trustStore.getURL(), "123456".toCharArray())
//                .build();
//        SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(sslContext);
//        HttpClient httpClient = HttpClients.custom()
//                .setSSLSocketFactory(socketFactory)
//                .setSSLHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER)
//                .build();
//        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);
//        return new RestTemplate(factory);

        RestTemplate restTemplate = new RestTemplate();
        KeyStore keyStore;
        HttpComponentsClientHttpRequestFactory requestFactory;
        try {
            keyStore = KeyStore.getInstance("pkcs12");
            ClassPathResource keyStoreResource = new ClassPathResource("ssl/keystore_client.p12");
            InputStream keyInputStream = keyStoreResource.getInputStream();
            keyStore.load(keyInputStream, "123456".toCharArray());
//
//            SSLContext sslContext = SSLContexts.custom()
//                    .loadKeyMaterial(keyStore, "123456".toCharArray())
//                    .loadTrustMaterial(keyStore, new TrustSelfSignedStrategy())
//                    .build();

            SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(new SSLContextBuilder()
                    .loadTrustMaterial(null, new TrustSelfSignedStrategy())
                    .loadKeyMaterial(keyStore, "123456".toCharArray()).build(), NoopHostnameVerifier.INSTANCE);

//            CloseableHttpClient httpClient = HttpClients.custom()
//                    .setSSLHostnameVerifier(SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER)
//                    .build();

            CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(socketFactory)
                    .setSSLHostnameVerifier(SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER)
//                    .setMaxConnTotal(5)
//                    .setMaxConnPerRoute(5)
                    .build();

            requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
//            requestFactory.setReadTimeout(10000);
//            requestFactory.setConnectTimeout(10000);

            restTemplate.setRequestFactory(requestFactory);

        } catch (Exception e) {
            System.out.println("RestTemplate creating error! " + e);
            e.printStackTrace();
        }
        return restTemplate;
    }

}
