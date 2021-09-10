package com.example.actuator_demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/health-check")
    public String healthCheck() {
        return "alive";
    }

}
