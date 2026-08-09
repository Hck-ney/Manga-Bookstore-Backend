package com.example.demo.cartItem.dto;

import com.example.demo.cartItem.entity.CartItem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public record CartItemResponse(
        Long id,
        Long cart_id,
        String manga_name,
        String author,
        Integer publication_year,
        String img_url,
        Integer quantity,
        BigDecimal price
) {
    public static CartItemResponse toResponse(CartItem cartItem){
        return new CartItemResponse(
                cartItem.getId(),
                cartItem.getCart().getId(),
                cartItem.getManga().getTitle(),
                cartItem.getManga().getAuthor(),
                cartItem.getManga().getPublication_year(),
                cartItem.getManga().getImg_url(),
                cartItem.getQuantity(),
                cartItem.getPrice()
        );
    }
    public static List<CartItemResponse> toListResponse(List<CartItem> list){
        List<CartItemResponse> result = new ArrayList<>();
        for(CartItem x: list){
            result.add(new CartItemResponse(
                    x.getId(),
                    x.getCart().getId(),
                    x.getManga().getTitle(),
                    x.getManga().getAuthor(),
                    x.getManga().getPublication_year(),
                    x.getManga().getImg_url(),
                    x.getQuantity(),
                    x.getPrice()
            ));
        }
        return result;
    }
}
