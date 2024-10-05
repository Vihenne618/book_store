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
public class ShoppingCartTest {

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
    }

    @Test
    public void test() throws IOException {
        // 1. add book to Shopping Cart
        System.out.println("[1] Test the Shopping Cart add API");
        ShoppingCartAddReq shoppingCartAddReq = new ShoppingCartAddReq(null, 1L);
        HttpEntity<ShoppingCartAddReq> add_request = new HttpEntity<>(shoppingCartAddReq);
        ResponseEntity<ShoppingCartResp> response = restTemplate.postForEntity("/shopping/cart/add", add_request, ShoppingCartResp.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getCartId()).isNotNull();
        Long cartId = response.getBody().getCartId();

        // 2. show Shopping Cart
        System.out.println("[2] Test the Shopping Cart list API");
        ResponseEntity<ShoppingCartResp> listResponse = restTemplate.exchange(
                "/shopping/cart/show?cartId="+cartId,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ShoppingCartResp>() {}
        );
        assertThat(listResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(listResponse.getBody().getBookList()).hasSize(1);
        BookResp bookResp = listResponse.getBody().getBookList().get(0);
        assertThat(bookResp.getBookTitle()).isEqualTo("Book 1");

        // 3. remove Shopping Cart item
        System.out.println("[3] Test the Shopping Cart remove API");
        Long bookId = bookResp.getBookId();
        ShoppingCartRemoveReq shoppingCartRemoveReq = new ShoppingCartRemoveReq(cartId, bookId);
        HttpEntity<ShoppingCartRemoveReq> remove_request = new HttpEntity<>(shoppingCartRemoveReq);
        ResponseEntity<Boolean> remove_response = restTemplate.exchange(
                "/shopping/cart/remove",
                HttpMethod.POST,
                remove_request,
                Boolean.class
        );
        assertThat(remove_response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(remove_response.getBody()).isTrue();
    }
}
