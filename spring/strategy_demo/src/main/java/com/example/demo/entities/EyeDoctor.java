package com.example.demo.entities;

import org.springframework.stereotype.Component;

@Component
public class EyeDoctor implements Doctor {
    @Override
    public void treat() {
        System.out.println("Just use these eye drops");
    }

    @Override
    public String myType() {
        return "eyeDoctor";
    }
}
