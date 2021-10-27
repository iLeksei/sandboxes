package com.example.sprinboot_statemachine_demo.dtos;

import com.example.sprinboot_statemachine_demo.entities.Mail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.jackson.JsonComponent;

import java.io.Serializable;

@JsonComponent
@Data
@NoArgsConstructor
public class MailDto implements Serializable {

    public static final long serialVersionUID = -670266347818013321L;

    public MailDto(String author, String address) {
        this.author = author;
        this.address = address;
    }

    private Long id;
    private String mailState;
    private String author;
    private String address;

    public static MailDto of(Mail mail) {
        MailDto mailDto = new MailDto();
        mailDto.setAuthor(mail.getAuthor());
        mailDto.setAddress(mail.getAddress());
        mailDto.setId(mail.getId());
        mailDto.setMailState(mail.getMailState().toString());
        return mailDto;
    }
}
