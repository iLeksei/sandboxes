package com.example.hibernate_demo.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.io.Serializable;

@ToString(exclude = {"account"})
@EqualsAndHashCode(exclude = {"account"})
@Data
@Table(name = "AUTHOR")
@Entity
public class Author implements Serializable {

    private static final long serialVersionUID = 876978506265052437L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_STORE_ID")
    @SequenceGenerator(name = "SEQ_STORE_ID", sequenceName = "SEQ_STORE_ID", initialValue = 1, allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @Column(name = "FULLNAME", nullable = false)
    private String fullName;

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL},  orphanRemoval = true, mappedBy = "author")
    private Account account;
}
