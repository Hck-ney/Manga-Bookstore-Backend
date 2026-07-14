package com.example.demo.orderDetails.dto;

public record OrderDetailsDTO(
        Long id,
        Long order_id,
        Long product_id,
        Integer quantity,
        Integer total
) {
}
