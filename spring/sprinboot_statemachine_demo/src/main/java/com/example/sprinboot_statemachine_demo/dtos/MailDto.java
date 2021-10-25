package com.example.sprinboot_statemachine_demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.jackson.JsonComponent;

import java.io.Serializable;

@JsonComponent
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailDto implements Serializable {

    public static final long serialVersionUID = -670266347818013321L;

    private String author;
    private String address;
}
