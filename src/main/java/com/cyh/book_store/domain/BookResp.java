package com.cyh.book_store.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BookResp {

    private Long bookId;

    private String bookTitle;

    private String bookAuthor;

    private BigDecimal bookPrice;

    private String category;
}
