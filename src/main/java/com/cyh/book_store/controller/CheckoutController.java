package com.cyh.book_store.controller;

import com.cyh.book_store.domain.CheckOutResp;
import com.cyh.book_store.service.CheckOutService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/checkout")
public class CheckoutController {

    @Autowired
    CheckOutService checkOutService;

    @PostMapping("/opera")
    @Operation( summary = "Check out the shopping cart", description = "Check out the shopping cart", method="GET")
    CheckOutResp checkout(@RequestParam Long cartId){
        return checkOutService.checkout(cartId);
    }



}
