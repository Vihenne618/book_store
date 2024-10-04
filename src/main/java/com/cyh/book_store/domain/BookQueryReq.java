package com.cyh.book_store.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BookQueryReq {

    private String bookTitle;

    private String bookAuthor;

    private String category;

}
