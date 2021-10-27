package com.example.sprinboot_statemachine_demo.utils;

import com.example.sprinboot_statemachine_demo.enums.MailEvent;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

public class MessageUtils {

    public static String MAIL_ID_HEADER = "MAIL_ID";

    public static Message<MailEvent> buildMessage(Long mailId, MailEvent event) {
        return MessageBuilder.withPayload(event)
                .setHeader(MAIL_ID_HEADER, mailId)
                .build();
    }

}
