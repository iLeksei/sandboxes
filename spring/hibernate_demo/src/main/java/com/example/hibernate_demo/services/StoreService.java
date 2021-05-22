package com.example.hibernate_demo.services;

import com.example.hibernate_demo.entities.Book;
import com.example.hibernate_demo.entities.Store;
import com.example.hibernate_demo.repositories.BookRepository;
import com.example.hibernate_demo.repositories.StoreRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Slf4j
@Service
public class StoreService {

    private BookRepository bookRepository;

    private StoreRepository storeRepository;

    private JdbcTemplate jdbcTemplate;

    private String GET_BOOK_AMOUNT_SQL = "SELECT sb.BOOK_AMOUNT FROM STORE_BOOK sb WHERE sb.STORE_ID = %d AND sb.BOOK_ID = %d;";
    private String SET_BOOK_AMOUNT_SQL = "UPDATE STORE_BOOK sb SET BOOK_AMOUNT = (" +
            "(SELECT sb.BOOK_AMOUNT FROM STORE_BOOK sb WHERE sb.BOOK_ID = %d AND STORE_ID = %d) - 1) " +
            "WHERE sb.BOOK_ID = %d AND sb. STORE_ID = %d AND sb.BOOK_AMOUNT > 0;";

    @PersistenceContext
    private EntityManager em;

    @Autowired
    StoreService(StoreRepository storeRepository, BookRepository bookRepository, JdbcTemplate jdbcTemplate) {
        this.storeRepository = storeRepository;
        this.jdbcTemplate = jdbcTemplate;
        this.bookRepository = bookRepository;
    }

    public long getBookAmountByStoreNameAndBookName(long storeId, long bookId) {
        LOG.info("Getting amount of particular book by bookId: {} and storeId: {}", bookId, storeId);
        String sql = String.format(GET_BOOK_AMOUNT_SQL, storeId, bookId);
        Query query = em.createNativeQuery(sql);
        Object result = query.getSingleResult();
        LOG.info("Amount: {} bookId: {}", result, bookId);
        return result != null ? Long.parseLong(result.toString()) : 0L;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Book sellBook(String storeName, String bookName) {
        LOG.info("Checking, does book: '{}' exists in store: '{}'", bookName, storeName);
        Book book = bookRepository.findBookByName(bookName);
        Store store = storeRepository.findByStoreName(storeName);
        if (book != null && store != null) {
            long bookAmount = this.getBookAmountByStoreNameAndBookName(store.getId(), book.getId());
            if (bookAmount > 0) {
                //Error: SQL command not properly ended
//                em.createNativeQuery(String.format(SET_BOOK_AMOUNT_SQL, store.getId(), book.getId(), store.getId(), book.getId())).getSingleResult();
                return book;
            }
        }
        return null;
    };

    public Store addStore(String name, String address) {
        LOG.info("Adding new store {} with address {} in database", name, address);
        Store store = new Store();
        store.setStoreName(name);
        store.setAddress(address);
        return  storeRepository.saveAndFlush(store);
    }

}
