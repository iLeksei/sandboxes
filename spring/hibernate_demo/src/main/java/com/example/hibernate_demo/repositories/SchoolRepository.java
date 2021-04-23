package com.example.hibernate_demo.repositories;

import com.example.hibernate_demo.entities.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolRepository extends JpaRepository<School, Long> {

    School findBySchoolNumber(int schoolNumber);
}
