package com.example.spring_integration_demo.services;

import com.example.spring_integration_demo.dto.MailDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class MailServiceImpl implements MailService {

    @Override
    public MailDto handleMail(MailDto mailDto) {
        LocalDateTime receiveTime = LocalDateTime.now();
        log.info("Got mail at: {} with author: {} and address: {}",
                receiveTime, mailDto.getAuthor(), mailDto.getAddress());

        mailDto.setProcessStartDateTime(receiveTime);
        return mailDto;
    }

}
