package com.example.hibernate_demo.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@ToString(exclude = {"storeList"})
@Table(name = "BOOK")
@SequenceGenerator(name = "SEQ_BOOK_ID", sequenceName = "SEQ_BOOK_ID", allocationSize = 1)
public class Book implements Serializable {
    private static final long serialVersionUID = -7541843379382730699L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_BOOK_ID")
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "STORE_BOOK",
            joinColumns = {@JoinColumn(name = "BOOK_ID")},
            inverseJoinColumns = {@JoinColumn(name = "STORE_ID")})
    private List<Store> storeList;
}
