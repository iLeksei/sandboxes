package com.example.ssl_server.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping(value = "/main")
    public String helloWorld() {
        System.out.println("");
        return "Hello, SSl world!";
    }
}