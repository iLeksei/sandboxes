package com.example.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/error/")
public class MainController {

    @GetMapping("/magic")
    public void aspectErrorDemo() throws Exception {
        System.out.println("U will see awesome aspect magic!");
        throw new Exception("aspect, try to catch me!");
    }

}
