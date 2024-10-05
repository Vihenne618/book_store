package com.cyh.book_store.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class BookAddReq {

    @NotBlank(message = "Book title cannot be empty")
    private String bookTitle;

    @NotBlank(message = "Book author cannot be empty")
    private String bookAuthor;

    @NotNull(message = "Book price cannot be null")
    private BigDecimal bookPrice;

    @NotBlank(message = "Book category cannot be empty")
    private String category;


}
