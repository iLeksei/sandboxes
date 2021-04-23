package com.example.hibernate_demo.services;

import com.example.hibernate_demo.entities.School;
import com.example.hibernate_demo.entities.SchoolChild;
import com.example.hibernate_demo.repositories.SchoolChildRepository;
import com.example.hibernate_demo.repositories.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolService {

    private SchoolRepository schoolRepository;
    private SchoolChildRepository schoolChildRepository;

    @Autowired
    SchoolService(SchoolRepository schoolRepository,
                  SchoolChildRepository schoolChildRepository) {
        this.schoolRepository = schoolRepository;
        this.schoolChildRepository = schoolChildRepository;
    }

    public School getSchool(int schoolNumber) {
        return schoolRepository.findBySchoolNumber(schoolNumber);
    }

    public List<SchoolChild> getSchoolChildrenBySchoolNumber(int schoolNumber) {
        return schoolChildRepository.findAllBySchoolNumber(schoolNumber);
    }

}
