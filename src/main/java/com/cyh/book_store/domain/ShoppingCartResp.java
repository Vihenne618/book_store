package com.cyh.book_store.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ShoppingCartResp {

    private Long cartId;

    private List<BookResp> bookList;

}
