package com.example.hibernate_demo.repositories;

import com.example.hibernate_demo.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    public Book findBookByName(String name);

    @Modifying
    public Book deleteByName(String name);
}
