package com.example.hibernate_demo.repositories;

import com.example.hibernate_demo.entities.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {

//    @Query(value = "SELECT sb.BOOK_AMOUNT FROM STORE_BOOK sb " +
//            "WHERE sb.STORE_ID =: storeId AND sb.BOOK_ID =:bookId ", nativeQuery = true)
//    long getBookAmountByIdAndStoreId(@Param("bookId") long bookId, @Param("storeId") long storeId);

    Store findByStoreName(String storeName);
}
