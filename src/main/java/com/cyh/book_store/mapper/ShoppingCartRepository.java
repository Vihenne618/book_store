package com.cyh.book_store.mapper;

import com.cyh.book_store.entity.ShoppingCart;
import com.cyh.book_store.entity.ShoppingCartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
}
