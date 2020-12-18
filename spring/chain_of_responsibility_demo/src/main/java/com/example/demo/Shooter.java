package com.example.demo;

import org.springframework.stereotype.Component;

@Component
public class Shooter implements AbilityHandler {
    @Override
    public void showAbility() {
        System.out.println("I am Shooter, i can use scope!");
    }
}
