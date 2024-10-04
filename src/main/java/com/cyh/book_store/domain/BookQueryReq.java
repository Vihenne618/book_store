package com.cyh.book_store.domain;

import lombok.Data;

@Data
public class BookQueryReq {

    private String bookTitle;

    private String bookAuthor;

    private String category;

}
