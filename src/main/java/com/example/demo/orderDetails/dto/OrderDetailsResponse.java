package com.example.demo.orderDetails.dto;

import com.example.demo.orderDetails.entity.OrderDetails;

import java.math.BigDecimal;

public record OrderDetailsResponse(
        Long id,
        String product_name,
        Integer quantity,
        BigDecimal price,
        BigDecimal total
) {
    public static OrderDetailsResponse from(OrderDetails detail) {
        return new OrderDetailsResponse(
                detail.getId(),
                detail.getManga().getTitle(),
                detail.getQuantity(),
                detail.getPrice(),
                detail.getTotal()
        );
    }
}