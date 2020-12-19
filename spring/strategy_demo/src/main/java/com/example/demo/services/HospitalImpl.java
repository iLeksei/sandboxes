package com.example.demo.services;

import com.example.demo.entities.DefaultDoctor;
import com.example.demo.entities.Doctor;
import com.example.demo.entities.Patient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

@Service
public class HospitalImpl implements Hospital {

    private Map<String, Doctor> doctorsMap;


    HospitalImpl(List<Doctor> doctors) {
        doctorsMap = doctors.stream().collect(toMap(Doctor::myType, Function.identity()));
    }

    @Override
    public void treat(Patient patient) {
        Doctor doctor = doctorsMap.getOrDefault(patient.getMethod(), new DefaultDoctor());
        doctor.treat();
    }
}
