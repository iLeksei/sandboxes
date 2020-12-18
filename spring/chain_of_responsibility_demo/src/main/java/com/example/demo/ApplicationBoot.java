package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ApplicationBoot {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ApplicationBoot.class, args);
//        context.getBean(MainService.class).showAbilities();
    }
}
