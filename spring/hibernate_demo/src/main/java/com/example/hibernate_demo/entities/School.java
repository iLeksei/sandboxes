package com.example.hibernate_demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@JsonInclude()
@EqualsAndHashCode
@ToString(exclude = {"gradeList"})
@Entity
@Table(name = "SCHOOL")
public class School implements Serializable {
    private static final long serialVersionUID = -2551075581923186300L;

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "ADDRESS", nullable = false, length = 255, unique = true)
    private String address;

    @Column(name = "SCHOOL_NUMBER", nullable = false, unique = true)
    private int schoolNumber;

    @JsonIgnore
    @JoinTable(name = "SCHOOL_GRADE",
            joinColumns = {@JoinColumn(name = "SCHOOL_ID")},
            inverseJoinColumns = {@JoinColumn(name = "GRADE_ID")})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Grade> gradeList;
}
