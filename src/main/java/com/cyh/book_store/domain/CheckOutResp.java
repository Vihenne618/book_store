package com.cyh.book_store.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CheckOutResp {

    private Long cartId;

    private BigDecimal totalPrice;

    private List<BookResp> bookList;
}
