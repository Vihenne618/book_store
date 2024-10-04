package com.cyh.book_store.mapper;

import com.cyh.book_store.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b WHERE (:bookTitle IS NULL OR b.bookTitle = :bookTitle) AND (:bookAuthor IS NULL OR b.bookAuthor = :bookAuthor) AND (:category IS NULL OR b.category = :category)")
    List<Book> findBookByConditions(String bookTitle, String bookAuthor, String category);



}
