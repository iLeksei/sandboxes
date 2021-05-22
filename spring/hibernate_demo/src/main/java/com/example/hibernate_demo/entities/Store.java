package com.example.hibernate_demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@Table(name = "STORE")
public class Store implements Serializable {
    private static final long serialVersionUID = 7211743451954143718L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_STORE_ID")
    @SequenceGenerator(name = "SEQ_STORE_ID", sequenceName = "SEQ_STORE_ID", initialValue = 1, allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @Column(name = "ADDRESS", nullable = false)
    private String address;

    @Column(name = "STORE_NAME", nullable = false)
    private String storeName;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "STORE_BOOK",
            joinColumns = {@JoinColumn(name = "STORE_ID")},
            inverseJoinColumns = {@JoinColumn(name = "BOOK_ID")})
    private List<Book> bookList;


}
