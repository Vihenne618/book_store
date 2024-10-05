package com.cyh.book_store;

import com.cyh.book_store.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CheckoutTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    public void setUp() {
        // add books
        List<BookAddReq> reqList = new ArrayList<>();
        BookAddReq bookAddReq = new BookAddReq("Book 1", "CYH", new BigDecimal("10.99"), "Science fiction");
        reqList.add(bookAddReq);
        bookAddReq = new BookAddReq("Book 2", "CXY", new BigDecimal("8.99"), "Science fiction");
        reqList.add(bookAddReq);
        bookAddReq = new BookAddReq("Book 3", "CYH", new BigDecimal("5.99"), "Science fiction");
        reqList.add(bookAddReq);
        for(BookAddReq req: reqList){
                HttpEntity<BookAddReq> add_request = new HttpEntity<>(req);
                restTemplate.postForEntity("/book/management/add", add_request, Boolean.class);
        }

        // list books
        BookQueryReq bookQueryReq = new BookQueryReq(null, null, null);
        HttpEntity<BookQueryReq> list_request = new HttpEntity<>(bookQueryReq);
        ResponseEntity<List<BookResp>> listResponse = restTemplate.exchange(
                "/book/management/list",
                HttpMethod.POST,
                list_request,
                new ParameterizedTypeReference<List<BookResp>>() {}
        );
        List<BookResp> books = listResponse.getBody();

        // add book to shopping cart
        Long cartId = null;
        for(BookResp book: books){
            ShoppingCartAddReq shoppingCartAddReq = new ShoppingCartAddReq(cartId, book.getBookId());
            HttpEntity<ShoppingCartAddReq> add_request = new HttpEntity<>(shoppingCartAddReq);
            ResponseEntity<ShoppingCartResp> response = restTemplate.postForEntity("/shopping/cart/add", add_request, ShoppingCartResp.class);
            cartId = response.getBody().getCartId();
        }
    }

    @Test
    public void testCheckOutOperations() throws IOException {
        System.out.println("[1] Test the CheckOut API");
        Long cartId = 1L;
        ResponseEntity<CheckOutResp> response = restTemplate.exchange(
                "/checkout/opera?cartId="+cartId,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<CheckOutResp>() {}
        );
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getBookList()).hasSize(3);
        assertThat(response.getBody().getTotalPrice()).isEqualTo(new BigDecimal("25.97"));
    }


}
