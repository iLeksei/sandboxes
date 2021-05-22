package com.example.hibernate_demo.repositories;

import com.example.hibernate_demo.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    public Author findAuthorByFullName(String fullName);
}
