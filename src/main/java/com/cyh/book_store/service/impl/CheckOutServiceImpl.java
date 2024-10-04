package com.cyh.book_store.service.impl;

import com.cyh.book_store.domain.BookResp;
import com.cyh.book_store.domain.CheckOutResp;
import com.cyh.book_store.domain.ShoppingCartResp;
import com.cyh.book_store.service.CheckOutService;
import com.cyh.book_store.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CheckOutServiceImpl implements CheckOutService {

    @Autowired
    ShoppingCartService shoppingCartService;

    @Override
    public CheckOutResp checkout(Long cartId) {
        CheckOutResp result = new CheckOutResp();
        result.setCartId(cartId);
        result.setTotalPrice(new BigDecimal("0"));
        // query the shopping cart
        ShoppingCartResp shoppingCartResp = shoppingCartService.showCart(cartId);
        List<BookResp> bookList = shoppingCartResp.getBookList();
        result.setBookList(bookList);
        if (bookList == null || bookList.isEmpty()){
            return result;
        }
        // count the totle amount
        BigDecimal totle = new BigDecimal("0");
        for(BookResp bookResp : bookList){
            if(bookResp.getBookPrice() != null){
                totle = totle.add(bookResp.getBookPrice());
            }
        }
        result.setTotalPrice(totle);
        return result;
    }
}
