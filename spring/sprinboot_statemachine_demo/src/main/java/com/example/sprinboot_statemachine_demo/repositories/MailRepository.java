package com.example.sprinboot_statemachine_demo.repositories;

import com.example.sprinboot_statemachine_demo.entities.Mail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MailRepository extends JpaRepository<Mail, Long> {
}
