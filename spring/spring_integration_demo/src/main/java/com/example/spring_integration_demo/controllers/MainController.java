package com.example.spring_integration_demo.controllers;

import com.example.spring_integration_demo.dto.MailDto;
import com.example.spring_integration_demo.enums.Header;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
public class MainController {

    private DirectChannel mailInputChannel;

    @Autowired
    MainController(DirectChannel mailInputChannel) {
        this.mailInputChannel = mailInputChannel;
    }

    @GetMapping("/health-check")
    public String healthCheck() {
        return "alive";
    }

    @PostMapping("/mail")
    public void postMail(@RequestBody MailDto mail) {
        log.info("Receive new mail: {}", mail);
        mailInputChannel.send(
                MessageBuilder.withPayload(mail)
                        .setHeader(Header.REGION_MESSAGE_HEADER.getValue(), mail.getRegion())
                        .build());
    }

}
