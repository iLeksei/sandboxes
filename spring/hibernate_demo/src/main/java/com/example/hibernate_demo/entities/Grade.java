package com.example.hibernate_demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode
@ToString(exclude = {"schoolList", "schoolChildList"})
@Entity
@Table(name = "GRADE")
public class Grade implements Serializable {
    private static final long serialVersionUID = -1782825714824076153L;
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "SCHOOL_GRADE",
        joinColumns = @JoinColumn(name = "GRADE_ID"),
        inverseJoinColumns = @JoinColumn(name = "SCHOOL_ID"))
    private List<School> schoolList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "grade")
    private List<SchoolChild> schoolChildList;

}
