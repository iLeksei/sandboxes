package com.example.hibernate_demo.controllers;

import com.example.hibernate_demo.entities.Book;
import com.example.hibernate_demo.entities.Store;
import com.example.hibernate_demo.services.BookService;
import com.example.hibernate_demo.services.StoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class MainController {
    private final BookService bookService;
    private final StoreService storeService;

    @Autowired
    MainController(BookService bookService, StoreService storeService) {
        this.bookService = bookService;
        this.storeService = storeService;
    }

    @GetMapping("health-check")
    public String healthCheck() {
        LOG.info("alive");
        return "alive";
    }

    @PostMapping("book")
    public Book addBook(@RequestParam String name) {
        LOG.info("Request for adding new Book with name: {}", name);
        return bookService.addBook(name);
    }

    @DeleteMapping("book")
    public void deleteBook(@RequestParam String name) {
        LOG.info("Request for deleting Book with name: {}", name);
        bookService.deleteBook(name);
    }

    @GetMapping("book")
    public String sellBook(@RequestParam("store") String storeName, @RequestParam("name") String bookName) {
      LOG.info("Request for selling book: {} in store: {}", bookName, storeName);
      Book book = storeService.sellBook(storeName, bookName);
      if (book != null) {
          return book.toString();
      } else {
          return "Not enough books :(";
      }
    }

    @PostMapping("store")
    public String addStore(@RequestParam String name, @RequestParam String address) {
        LOG.info("Request for adding new store: {} with address: {}", name, address);
        Store newStore = storeService.addStore(name, address);
        return newStore != null ? newStore.toString() : "sorry, i can't add new store :(";
    }








}
