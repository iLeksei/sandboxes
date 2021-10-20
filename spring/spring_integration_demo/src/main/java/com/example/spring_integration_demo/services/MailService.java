package com.example.spring_integration_demo.services;

import com.example.spring_integration_demo.dto.MailDto;

public interface MailService {

    MailDto handleMail(MailDto mailDto);
}
