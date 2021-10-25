package com.example.sprinboot_statemachine_demo.controllers;

import com.example.sprinboot_statemachine_demo.dtos.MailDto;
import com.example.sprinboot_statemachine_demo.entities.Mail;
import com.example.sprinboot_statemachine_demo.services.MailServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.OutputKeys;


@Slf4j
@RestController
public class MailController {

    private final MailServiceImpl mailService;

    @Autowired
    MailController(MailServiceImpl mailService) {
        this.mailService = mailService;
    }

    @GetMapping("/health-check")
    public String healthCheck() {
        return "alive";
    }


    @PostMapping(value = "/mail", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HttpStatus> sendMail(@RequestBody MailDto mail) {
        log.info("Got new mail: {}", mail);
        mailService.registerNewMail(mail);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
