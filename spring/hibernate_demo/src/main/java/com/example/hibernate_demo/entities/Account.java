package com.example.hibernate_demo.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
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
    @Column(name = "ACC_ID", nullable = false, unique = true)
    @GeneratedValue(generator = "SEQ_STORE_ID")
    @GenericGenerator(strategy = "foreign", name="SEQ_STORE_ID",
            parameters = @Parameter(name = "property", value="author"))
    private Long id;

    @Column(name = "LOGIN", nullable = false, length = 25)
    private String login;

//    @PrimaryKeyJoinColumn(name = "ACC_ID", referencedColumnName = "ID")
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "account")
    private Author author;
}
