package com.cyh.book_store.service.impl;

import com.cyh.book_store.domain.*;
import com.cyh.book_store.entity.Book;
import com.cyh.book_store.entity.ShoppingCart;
import com.cyh.book_store.entity.ShoppingCartItem;
import com.cyh.book_store.mapper.BookRepository;
import com.cyh.book_store.mapper.ShoppingCartItemRepository;
import com.cyh.book_store.mapper.ShoppingCartRepository;
import com.cyh.book_store.service.ShoppingCartService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private ShoppingCartItemRepository shoppingCartItemRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public ShoppingCartResp AddToCart(ShoppingCartAddReq req) {
        // check book
        Optional<Book> book = bookRepository.findById(req.getBookId());
        if (book.isEmpty()){
            return null;
        }

        // check ShoppingCart
        ShoppingCart shoppingCart = new ShoppingCart();
        if (req.getCartId() == null){
            shoppingCartRepository.save(shoppingCart);
        }else {
            Optional<ShoppingCart> shoppingCartOptional = shoppingCartRepository.findById(req.getCartId());
            if(shoppingCartOptional.isEmpty()){
                shoppingCartRepository.save(shoppingCart);
            }else {
                shoppingCart = shoppingCartOptional.get();
            }
        }

        //
        ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
        shoppingCartItem.setCartId(shoppingCart.getCartId());
        shoppingCartItem.setBookId(req.getBookId());
        shoppingCartItemRepository.save(shoppingCartItem);
        return this.showCart(shoppingCart.getCartId());
    }

    @Override
    public Boolean removeFromCart(ShoppingCartRemoveReq req) {
        ShoppingCartItem shoppingCartItem = shoppingCartItemRepository.findByCartIdAndBookId(req.getCartId(), req.getBookId());
        if (shoppingCartItem == null){
            return false;
        }
        shoppingCartItemRepository.deleteById(shoppingCartItem.getCartItemId());
        return true;
    }

    @Override
    public ShoppingCartResp showCart(Long cartId) {
        ShoppingCartResp shoppingCartResp = new ShoppingCartResp();
        shoppingCartResp.setCartId(cartId);
        // query the shopping cart item
        List<ShoppingCartItem> shoppingCartItems = shoppingCartItemRepository.findByCartId(cartId);
        if (shoppingCartItems.isEmpty()){
            return shoppingCartResp;
        }

        List<BookResp> bookList = new ArrayList<>();
        for(ShoppingCartItem item: shoppingCartItems){
            Optional<Book> book = bookRepository.findById(item.getBookId());
            if (book.isEmpty()){
                continue;
            }
            BookResp bookResp = new BookResp();
            BeanUtils.copyProperties(book.get(), bookResp);
            bookList.add(bookResp);
        }
        shoppingCartResp.setBookList(bookList);
        return shoppingCartResp;
    }


}
