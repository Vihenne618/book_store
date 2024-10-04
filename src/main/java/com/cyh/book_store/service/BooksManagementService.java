package com.cyh.book_store.service;

import com.cyh.book_store.domain.BookAddReq;
import com.cyh.book_store.domain.BookQueryReq;
import com.cyh.book_store.domain.BookResp;
import com.cyh.book_store.entity.Book;

import java.util.List;

public interface BooksManagementService {

    List<BookResp> listBooks(BookQueryReq req);

    Boolean AddBooks(BookAddReq req);

    Boolean RemoveBooks(Long bookId);
}
