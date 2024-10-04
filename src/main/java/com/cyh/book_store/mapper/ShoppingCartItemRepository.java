package com.cyh.book_store.mapper;

import com.cyh.book_store.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartItemRepository extends JpaRepository<ShoppingCart, Long> {
}
