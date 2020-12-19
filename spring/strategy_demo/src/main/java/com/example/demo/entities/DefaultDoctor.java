package com.example.demo.entities;

import org.springframework.stereotype.Component;

@Component
public class DefaultDoctor implements Doctor {
    @Override
    public void treat() {
        System.out.println("Will pass by itself!");
    }

    @Override
    public String myType() {
        return "defaultDoctor";
    }
}
