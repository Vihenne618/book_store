package com.cyh.book_store.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ShoppingCartAddReq {

    private Long cartId;

    @NotBlank(message = "Book id cannot be empty")
    private Long bookId;
}
