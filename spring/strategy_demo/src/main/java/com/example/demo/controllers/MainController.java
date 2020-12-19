package com.example.demo.controllers;

import com.example.demo.entities.Patient;
import com.example.demo.services.Hospital;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/hospital/")
@RestController
public class MainController {

    @Autowired
    private Hospital hospital;

    @GetMapping("health-check")
    public String healthCheck() {
        System.out.println("alive");
        return "alive";
    }

    @GetMapping("treat")
    public String useTreat() {
        System.out.println("Start treatment...");
        Patient patient = new Patient("bob", 32, "eyeDoctor");
        hospital.treat(patient);
        return patient.getName() + " was treated!";
    }

}
