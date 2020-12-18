package com.example.demo;

import org.springframework.stereotype.Component;

@Component
public class Wrestler implements AbilityHandler {
    @Override
    public void showAbility() {
        System.out.println("I can throw you, I am Judoka!");
    }
}
