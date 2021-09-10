package com.example.hibernate_demo.services;

import com.example.hibernate_demo.entities.Account;
import com.example.hibernate_demo.entities.Author;
import com.example.hibernate_demo.repositories.AccountRepository;
import com.example.hibernate_demo.repositories.AuthorRepository;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final AccountRepository accountRepository;

    @Autowired
    AuthorService(AuthorRepository authorRepository, AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
        this.authorRepository = authorRepository;
    }

    @Transactional
    public Author createAuthor(@NonNull String authorFullName, @NonNull String login) {
        try {
            LOG.info("Start to create author with fullName: {} and login: {}", authorFullName, login);
            Author author = new Author();
            author.setFullName(authorFullName);
            authorRepository.saveAndFlush(author);

            Account account = createAccount(author, login);
            author.setAccount(account);
//            authorRepository.saveAndFlush(author);
            accountRepository.save(account);
            return author;
        } catch (Exception e) {
            LOG.error("Gor error with creating author with fullName: {}", authorFullName);
            e.printStackTrace();
        }
        return null;
    }

    Account createAccount(Author author, String login) {
        Account account = new Account();
//        account.setId(author.getId());
        account.setAuthor(author);
        account.setLogin(login);
        return account;
    }

    @Transactional
    public String deleteAuthor(String fullName) {
        try {
            Author author = authorRepository.findAuthorByFullName(fullName);
            accountRepository.delete(author.getAccount());
            authorRepository.delete(author);
            return "OK";
        } catch (Exception e) {
            e.printStackTrace();
            return "ERROR";
        }
    }
}
