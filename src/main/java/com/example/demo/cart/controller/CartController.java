package com.example.demo.cart.controller;

import com.example.demo.cart.dto.CartRequest;
import com.example.demo.cart.dto.CartResponse;
import com.example.demo.cart.services.CartServices;
import com.example.demo.cartItem.dto.CartItemRequest;
import com.example.demo.cartItem.dto.UpdateQuantityRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
public class CartController {
    @Autowired
    private CartServices services;

    @PostMapping("/cart")
    public CartResponse createCart(@Valid @RequestBody CartRequest request){
        return services.createCart(request);
    }


    @GetMapping("/cart")
    public CartResponse getCart(@Valid Authentication authentication){
        return services.getCart(authentication.getName());
    }

    @PostMapping("/cart/items")
    public CartResponse addItem(@Valid Authentication authentication, @RequestBody CartItemRequest request) {
        return services.addItemToCart(authentication.getName(), request);
    }

    @PatchMapping("/cart/items/{itemId}")
    public CartResponse updateItem(@Valid @PathVariable Long itemId, @RequestBody UpdateQuantityRequest request) {
        return services.updateItemQuantity(itemId, request.quantity());
    }

    @DeleteMapping("/cart/items/{itemId}")
    public CartResponse removeItem(@Valid @PathVariable Long itemId) {
        return services.removeItemFromCart(itemId);
    }

    @DeleteMapping("/cart/{id}")
    public void deleteCart(@Valid @PathVariable Long id){
        services.deleteCart(id);
    }
}
