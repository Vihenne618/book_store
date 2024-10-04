package com.cyh.book_store.controller;

import com.cyh.book_store.domain.BookAddReq;
import com.cyh.book_store.domain.ShoppingCartAddReq;
import com.cyh.book_store.domain.ShoppingCartRemoveReq;
import com.cyh.book_store.domain.ShoppingCartResp;
import com.cyh.book_store.service.ShoppingCartService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shopping/cart")
public class ShoppingCartController {

    @Autowired
    ShoppingCartService shoppingCartService;

    @PostMapping("/add")
    @Operation( summary = "Add books to Shopping Car", description = "Add books to Shopping Car", method="POST")
    ShoppingCartResp AddToCart(@RequestBody ShoppingCartAddReq req){
        return shoppingCartService.AddToCart(req);
    }

    @PostMapping("/remove")
    @Operation( summary = "Remove books from Shopping Car", description = "Remove books from Shopping Car", method="POST")
    Boolean removeFromCart(@RequestBody ShoppingCartRemoveReq req){
        return shoppingCartService.removeFromCart(req);
    }

    @PostMapping("/show")
    @Operation( summary = "List Shopping Car", description = "List Shopping Car", method="GET")
    ShoppingCartResp showCart(@RequestParam Long cartId){
        return shoppingCartService.showCart(cartId);
    }
}
