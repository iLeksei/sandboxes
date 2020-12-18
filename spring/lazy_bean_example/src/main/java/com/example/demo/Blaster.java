package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Lazy
public class Blaster {

    @PostConstruct
    private void init() {
        System.out.println("loading gun!!!");
    }

    @Bean
    public void shoot() {
        System.out.println("piu piu piu...");
    }

}
