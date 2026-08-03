package com.example.demo.cartItem.dto;

import com.example.demo.cartItem.entity.CartItem;

import java.math.BigDecimal;

public record CartItemRequest(
        Long cart_id,
        Long manga_id,
        Integer quantity,
        BigDecimal price
) {
}
