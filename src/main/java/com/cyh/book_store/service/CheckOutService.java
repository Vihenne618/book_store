package com.cyh.book_store.service;

import com.cyh.book_store.domain.CheckOutResp;

public interface CheckOutService {

    CheckOutResp checkout(Long cartId);
}
