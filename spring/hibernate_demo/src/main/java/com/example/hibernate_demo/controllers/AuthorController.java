package com.example.hibernate_demo.controllers;

import com.example.hibernate_demo.entities.Author;
import com.example.hibernate_demo.services.AuthorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping("/author")
    public String createNewAuthor(@RequestParam("fullName") String fullName,
                                                  @RequestParam("login") String login) {
      LOG.info("Got fullName {} and login {} for new author", fullName, login);
      Author author = authorService.createAuthor(fullName, login);

      if (author != null) {
        LOG.info("Was created new author: {}", author);
        return author.toString();
      } else {
        return "";
      }
    }

    @DeleteMapping("/author")
    public String deleteAuthor(@RequestParam("fullName") String fullName) {
        LOG.info("Will delete author with fullName: {}", fullName);
        String result = authorService.deleteAuthor(fullName);
        return "OK".equals(result) ? "OK" : "ERROR";
    }

}
