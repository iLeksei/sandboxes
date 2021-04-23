package com.example.hibernate_demo.controllers;

import com.example.hibernate_demo.entities.School;
import com.example.hibernate_demo.entities.SchoolChild;
import com.example.hibernate_demo.services.SchoolService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/school")
public class SchoolController {

    private SchoolService schoolService;

    @Autowired
    SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @GetMapping(value = "/{school_number}")
    public String getSchool(@PathVariable("school_number") int schoolNumber) throws JsonProcessingException {
        School school = schoolService.getSchool(schoolNumber);
        return new ObjectMapper().writeValueAsString(school);
    }

    @GetMapping(value = "/{school_number}/school_children")
    public String getSchoolChildrenList(@PathVariable("school_number") int schoolNumber) throws JsonProcessingException {
        List<SchoolChild> children = schoolService.getSchoolChildrenBySchoolNumber(schoolNumber);
        if (children != null && children.size() > 0) {
            return new ObjectMapper().writeValueAsString(children);
        }
        return "";
    }

}
