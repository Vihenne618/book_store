package com.cyh.book_store.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookQueryReq {

    private String bookTitle;

    private String bookAuthor;

    private String category;

}
