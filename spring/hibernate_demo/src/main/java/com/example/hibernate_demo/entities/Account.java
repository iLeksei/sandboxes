package com.example.hibernate_demo.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.io.Serializable;

@ToString(exclude = {"author"})
@EqualsAndHashCode(exclude = {"author"})
@Entity
@Data
@NoArgsConstructor
@Table(name = "ACCOUNT")
public class Account implements Serializable {

    private static final long serialVersionUID = -3137902722796945521L;

    @Id
    @Column(name = "ACC_ID", nullable = true)
    private Long id;

    @Column(name = "LOGIN", nullable = false, length = 25)
    private String login;

//    @MapsId("id")
    @PrimaryKeyJoinColumn(name = "ACC_ID", referencedColumnName = "ID")
    @OneToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "ACC_ID", nullable = true)
    private Author author;

    public void setAuthor(Author author) {
//        this.id = author != null ? author.getId() : null;
        this.author = author;
    }
}
