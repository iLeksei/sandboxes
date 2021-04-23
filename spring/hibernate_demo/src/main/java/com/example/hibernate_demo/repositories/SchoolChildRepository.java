package com.example.hibernate_demo.repositories;

import com.example.hibernate_demo.entities.SchoolChild;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchoolChildRepository extends JpaRepository<SchoolChild, Long> {

    @Query("select ch from SchoolChild ch where ch.schoolNumber =:schoolNumber")
    List<SchoolChild> findAllBySchoolNumber(@Param("schoolNumber") int schoolNumber);
}
