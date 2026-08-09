package com.example.demo.cart.controller;

import com.example.demo.cart.dto.CartRequest;
import com.example.demo.cart.dto.CartResponse;
import com.example.demo.cart.entity.Cart;
import com.example.demo.cart.services.CartServices;
import com.example.demo.cartItem.dto.CartItemRequest;
import com.example.demo.cartItem.dto.UpdateQuantityRequest;
import com.example.demo.cartItem.services.CartItemServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CartController {
    @Autowired
    private CartServices services;

    @PostMapping("/cart")
    public CartResponse createCart(@Valid @RequestBody CartRequest request){
        return services.createCart(request);
    }


    @GetMapping("/cart")
    public CartResponse getCart(Authentication authentication){
        return services.getCart(authentication.getName());
    }

    @PostMapping("/cart/items")
    public CartResponse addItem(Authentication authentication, @RequestBody CartItemRequest request) {
        return services.addItemToCart(authentication.getName(), request);
    }

    @PatchMapping("/cart/items/{itemId}")
    public CartResponse updateItem(@PathVariable Long itemId, @RequestBody UpdateQuantityRequest request) {
        return services.updateItemQuantity(itemId, request.quantity());
    }

    @DeleteMapping("/cart/items/{itemId}")
    public CartResponse removeItem(@PathVariable Long itemId) {
        return services.removeItemFromCart(itemId);
    }

    @DeleteMapping("/cart/{id}")
    public void deleteCart(@PathVariable Long id){
        services.deleteCart(id);
    }
}
