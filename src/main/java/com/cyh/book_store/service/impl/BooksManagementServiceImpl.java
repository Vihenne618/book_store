package com.cyh.book_store.service.impl;

import com.cyh.book_store.domain.BookAddReq;
import com.cyh.book_store.domain.BookQueryReq;
import com.cyh.book_store.domain.BookResp;
import com.cyh.book_store.entity.Book;
import com.cyh.book_store.mapper.BookRepository;
import com.cyh.book_store.service.BooksManagementService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BooksManagementServiceImpl implements BooksManagementService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<BookResp> listBooks(BookQueryReq req) {
        List<Book> resList = bookRepository.findBookByConditions(req.getBookTitle(), req.getBookAuthor(), req.getCategory());
        List<BookResp> result = new ArrayList<>();
        if(resList == null || resList.isEmpty()){
            return result;
        }
        for(Book book : resList){
            BookResp bookResp = new BookResp();
            BeanUtils.copyProperties(book, bookResp);
            result.add(bookResp);
        }
        return result;
    }

    @Override
    public Boolean AddBooks(BookAddReq req) {
        Book book = new Book();
        BeanUtils.copyProperties(req, book);
        bookRepository.save(book);
        return true;
    }

    @Override
    public Boolean RemoveBooks(Long bookId) {
        if (bookId == null){
            return false;
        }
        Optional<Book> book = bookRepository.findById(bookId);
        if (book.isEmpty()){
            return false;
        }
        bookRepository.deleteById(bookId);
        return true;
    }
}
