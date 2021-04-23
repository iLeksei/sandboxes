package com.example.service_demo.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class MainController {

    AtomicInteger counter = new AtomicInteger(0);

    @Value("${server.port}")
    private String servicePort;

    @GetMapping(value = "service")
    public String callService() {
        System.out.println("service with port: " + servicePort + " was called, count will be: " + counter.incrementAndGet());
        return servicePort;
    }

}
