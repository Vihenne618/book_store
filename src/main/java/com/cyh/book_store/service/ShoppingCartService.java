package com.cyh.book_store.service;

import com.cyh.book_store.domain.BookAddReq;
import com.cyh.book_store.domain.ShoppingCartAddReq;
import com.cyh.book_store.domain.ShoppingCartRemoveReq;
import com.cyh.book_store.domain.ShoppingCartResp;

public interface ShoppingCartService {

    ShoppingCartResp AddToCart(ShoppingCartAddReq req);

    Boolean removeFromCart(ShoppingCartRemoveReq req);

    ShoppingCartResp showCart(Long cartId);
}
