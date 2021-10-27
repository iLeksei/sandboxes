package com.example.sprinboot_statemachine_demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.DEFINED_PORT)
class ApplicationTests {

    @Autowired
    RestTemplate restTemplate;

    @Test
    void contextLoads() {
        String response = restTemplate.getForObject("http://localhost:8080/health-check", String.class);
        Assertions.assertEquals("alive", response);
    }

}
