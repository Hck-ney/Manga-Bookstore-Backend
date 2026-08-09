package com.example.demo.cartItem.dto;

import com.example.demo.cartItem.entity.CartItem;
import com.example.demo.exceptions.OrderException;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;

public record CartItemRequest(
        Long manga_id,
        Integer quantity,
        BigDecimal price
) {
}
