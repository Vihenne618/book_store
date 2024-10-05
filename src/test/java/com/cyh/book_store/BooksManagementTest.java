package com.cyh.book_store;

import com.cyh.book_store.domain.BookAddReq;
import com.cyh.book_store.domain.BookQueryReq;
import com.cyh.book_store.domain.BookResp;
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
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BooksManagementTest {

    @Autowired
    private TestRestTemplate restTemplate;

    // test method
    @Test
    public void test() throws IOException {
        // 1. add book
        System.out.println("[1] Test the book add API");
        BookAddReq bookAddReq = new BookAddReq("Book 1", "CYH", new BigDecimal("10.99"), "Science fiction");
        HttpEntity<BookAddReq> add_request = new HttpEntity<>(bookAddReq);
        ResponseEntity<Boolean> response = restTemplate.postForEntity("/book/management/add", add_request, Boolean.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isTrue();


        // 2. list book
        System.out.println("[2] Test the book list API");
        BookQueryReq bookQueryReq = new BookQueryReq(null, "CYH", null);
        HttpEntity<BookQueryReq> list_request = new HttpEntity<>(bookQueryReq);
        ResponseEntity<List<BookResp>> listResponse = restTemplate.exchange(
                "/book/management/list",
                HttpMethod.POST,
                list_request,
                new ParameterizedTypeReference<List<BookResp>>() {}
        );
        assertThat(listResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(listResponse.getBody()).hasSize(1);
        BookResp bookResp = listResponse.getBody().get(0);
        assertThat(bookResp.getBookTitle()).isEqualTo("Book 1");

        // 3. remove book
        System.out.println("[3] Test the book remove API");
        Long bookId = bookResp.getBookId();
        ResponseEntity<Boolean> remove_response = restTemplate.exchange(
                "/book/management/remove?bookId=" + bookId,
                HttpMethod.GET,
                null,
                Boolean.class
        );
        assertThat(remove_response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(remove_response.getBody()).isTrue();

        // check remove the book
        bookQueryReq = new BookQueryReq(null, null, null);
        list_request = new HttpEntity<>(bookQueryReq);
        listResponse = restTemplate.exchange(
                "/book/management/list",
                HttpMethod.POST,
                list_request,
                new ParameterizedTypeReference<List<BookResp>>() {}
        );
        assertThat(listResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(listResponse.getBody()).hasSize(0);
    }
}
