package com.cyh.book_store.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ShoppingCartRemoveReq {

    @NotBlank(message = "Cart id cannot be empty")
    private Long cartId;

    @NotBlank(message = "Book id cannot be empty")
    private Long bookId;
}
