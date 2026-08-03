package com.example.demo.cart.controller;

import com.example.demo.cart.dto.CartRequest;
import com.example.demo.cart.entity.Cart;
import com.example.demo.cart.services.CartServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CartController {
    @Autowired
    private CartServices services;

    @PostMapping("/cart")
    public Cart createCart(CartRequest request){
        return services.createCart(request);
    }

    public List<Cart> getAllCart(){
        return services.getAllCart();
    }
}
