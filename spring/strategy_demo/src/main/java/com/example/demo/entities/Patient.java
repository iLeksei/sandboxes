package com.example.demo.entities;


import lombok.*;


@ToString
public class Patient {
    private String name;
    private int age;
    private String method;

    Patient() {};

    public Patient(String name, int age, String method) {
        this.name = name;
        this.age = age;
        this.method = method;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
