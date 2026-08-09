package com.example.demo.cart.dto;

import com.example.demo.cart.entity.Cart;
import com.example.demo.cartItem.dto.CartItemResponse;

import java.util.ArrayList;
import java.util.List;

public record CartResponse(
        Long id,
        Long UserId,
        List<CartItemResponse> itemList
) {
    public static List<CartResponse> toListEntity(List<Cart> cart){
        List<CartResponse> result = new ArrayList<>();
        for(Cart x: cart){
            result.add(
                    new CartResponse(
                            x.getId(),
                            x.getUser().getId(),
                            CartItemResponse.toListResponse(x.getCart_items())
                    )
            );
        }
        return result;
    }
    public static CartResponse toResponse(Cart cart){
        return new CartResponse(
                cart.getId(),
                cart.getUser().getId(),
                CartItemResponse.toListResponse(cart.getCart_items())
        );
    }
}
