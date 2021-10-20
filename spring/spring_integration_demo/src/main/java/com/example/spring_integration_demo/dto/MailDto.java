package com.example.spring_integration_demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailDto implements Serializable {

    static final long serialVersionUID = 0L;

    private String author;
    private String address;
    private LocalDateTime processStartDateTime;
    private String region;
    private boolean hasMarks;
}
