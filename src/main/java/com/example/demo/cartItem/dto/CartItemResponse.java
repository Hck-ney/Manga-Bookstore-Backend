package com.example.demo.cartItem.dto;

import com.example.demo.cartItem.entity.CartItem;

import java.math.BigDecimal;

public record CartItemResponse(
        Long id,
        Long cart_id,
        String manga_name,
        Integer quantity,
        BigDecimal price
) {
    public static CartItemResponse toResponse(CartItem cartItem){
        return new CartItemResponse(
                cartItem.getId(),
                cartItem.getCart().getId(),
                cartItem.getManga().getTitle(),
                cartItem.getQuantity(),
                cartItem.getPrice()
        );
    }
}
