package com.example.hibernate_demo.services;

import com.example.hibernate_demo.entities.Book;
import com.example.hibernate_demo.repositories.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BookService {

    private BookRepository bookRepository;

    @Autowired
    BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void deleteBook(String name) {
        LOG.info("Trying to delete book with name: {}", name);
        Book book = bookRepository.findBookByName(name);
        if (book != null) {
            LOG.info("book {} will remove...", book);
            bookRepository.delete(book);
        } else {
            LOG.info("Book {} not found", name);
        }
    }


    public Book addBook(String bookName) {
        LOG.info("Trying to add new Book with name: {}", bookName);
        Book newBook = new Book();
        newBook.setName(bookName);
        return bookRepository.saveAndFlush(newBook);
    }

}
