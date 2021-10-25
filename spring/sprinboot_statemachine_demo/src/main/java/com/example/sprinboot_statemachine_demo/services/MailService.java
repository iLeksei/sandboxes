package com.example.sprinboot_statemachine_demo.services;

import com.example.sprinboot_statemachine_demo.dtos.MailDto;
import com.example.sprinboot_statemachine_demo.entities.Mail;

public interface MailService {

    public void registerNewMail(MailDto newMail);

}
