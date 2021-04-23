package com.example.hibernate_demo.entities;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@EqualsAndHashCode
@ToString(exclude = {"school", "grade"})
@Table(name = "SCHOOLCHILD")
@Entity
public class SchoolChild implements Serializable {
    private static final long serialVersionUID = -2590267049480498867L;

    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "AGE", nullable = false)
    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    private School schoolNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    private Grade grade;
}
