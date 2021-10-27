package com.example.sprinboot_statemachine_demo.entities;

import com.example.sprinboot_statemachine_demo.enums.MailState;
import lombok.*;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.*;

@Entity
@Table(name = "mail")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Mail {

    @ToString.Include
    @Id
    @GeneratedValue
    private Long id;

    @ToString.Include
    @Column( name = "AUTHOR", nullable = false, length = 20)
    private String author;

    @ToString.Include
    @Column(name = "ADDRESS", nullable = false, length = 20)
    private String address;

    @ToString.Include
    @Enumerated(EnumType.STRING)
    private MailState mailState;

    public boolean isValid() {
        return this.getAuthor() != null && this.getAddress() != null;
    }
}
