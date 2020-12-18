package com.example.demo;

import org.springframework.stereotype.Component;

@Component
public class Runner implements AbilityHandler {
    @Override
    public void showAbility() {
        System.out.println("I am Runner, i can run so fast!");
    }
}
