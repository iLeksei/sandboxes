package com.example.demo.entities;

import org.springframework.stereotype.Component;

@Component
public class Surgeon implements Doctor {
    @Override
    public void treat() {
        System.out.println("We need to do surgery!");
    }

    @Override
    public String myType() {
        return "surgeon";
    }
}
