package com.example.demo.cartItem.controller;

import com.example.demo.cartItem.entity.CartItem;
import com.example.demo.cartItem.services.CartItemServices;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CartItemController {

    private CartItemServices services;

    @PostMapping("/cart-item")
    public CartItem createCartItem(CartItem cartItem){
        return services.createCartItem(cartItem);
    }

    public List<CartItem> getAllCartItem(){
        return services.getAllCartItem();
    }

}
