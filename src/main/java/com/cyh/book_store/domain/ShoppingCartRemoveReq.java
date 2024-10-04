package com.cyh.book_store.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ShoppingCartRemoveReq {

    @NotBlank(message = "Cart id cannot be empty")
    private Long cartId;

    @NotBlank(message = "Book id cannot be empty")
    private Long bookId;
}
