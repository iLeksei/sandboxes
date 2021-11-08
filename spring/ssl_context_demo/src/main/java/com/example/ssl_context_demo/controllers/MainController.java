package com.example.ssl_context_demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MainController {

    private RestTemplate restSecured;

    @Autowired
    MainController(@Qualifier("restSecured") RestTemplate restSecured) {
        this.restSecured = restSecured;
    }

    @GetMapping("/health-check")
    public String healthCheck() {
        return "server is alive";
    }

    @GetMapping("/check-stub")
    public String checkStub() {
        return restSecured.getForObject("https://localhost:1415/health-check", String.class);
    }

}
