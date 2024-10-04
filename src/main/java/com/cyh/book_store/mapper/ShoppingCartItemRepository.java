package com.cyh.book_store.mapper;

import com.cyh.book_store.entity.ShoppingCart;
import com.cyh.book_store.entity.ShoppingCartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShoppingCartItemRepository extends JpaRepository<ShoppingCartItem, Long> {

    ShoppingCartItem findByCartIdAndBookId(Long cartId, Long bookId);

    List<ShoppingCartItem> findByCartId(Long cartId);


}
