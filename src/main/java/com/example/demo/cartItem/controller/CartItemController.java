package com.example.demo.cartItem.controller;

import com.example.demo.cartItem.entity.CartItem;
import com.example.demo.cartItem.services.CartItemServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartItemController {

    @Autowired
    private CartItemServices services;

    @PostMapping("/cart-item")
    public CartItem createCartItem(CartItem cartItem){
        return services.createCartItem(cartItem);
    }

//    public List<CartItem> getAllCartItem(){
//        return services.getAllCartItem();
//    }

    @DeleteMapping("/cart/item/{id}")
    public void deleteCartItem(Authentication authentication, @PathVariable Long id){
        services.deleteCartItem(authentication.getName(), id);
    }
}
