package com.example.ssl_client.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MainController {

    RestTemplate restTemplate;
    private String serverEndpoint = "https://localhost:8443/main";

    @Autowired
    public MainController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping(value = "main")
    public String getMainMessage() {
        System.out.println("server endpoint: " + serverEndpoint);
        String response = restTemplate.getForObject(serverEndpoint, String.class);
        return response;
    }

}
