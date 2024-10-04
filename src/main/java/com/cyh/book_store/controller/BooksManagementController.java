package com.cyh.book_store.controller;

import com.cyh.book_store.domain.BookAddReq;
import com.cyh.book_store.domain.BookQueryReq;
import com.cyh.book_store.domain.BookResp;
import com.cyh.book_store.service.BooksManagementService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book/management")
public class BooksManagementController {

    @Autowired
    private BooksManagementService booksManagementService;

    @PostMapping("/list")
    @Operation( summary = "list books by condition", description = "list books by condition", method="POST")
    List<BookResp> listBooks(@RequestBody BookQueryReq req){
        return booksManagementService.listBooks(req);
    }

    @PostMapping("/add")
    @Operation( summary = "add a book", description = "add a book", method="POST" )
    Boolean AddBooks(@RequestBody BookAddReq req){
        return booksManagementService.AddBooks(req);
    }

    @GetMapping("/remove")
    @Operation( summary = "remove a book", description = "remove a book", method="GET" )
    Boolean RemoveBooks(@PathVariable Long bookId){
        return booksManagementService.RemoveBooks(bookId);
    }

}
