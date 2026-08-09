package com.example.demo.cart.dto;

import com.example.demo.cartItem.dto.CartItemRequest;
import com.example.demo.cartItem.entity.CartItem;

import java.util.List;

public record CartRequest (
        Long user_id,
        List<CartItemRequest> itemList
){
}
