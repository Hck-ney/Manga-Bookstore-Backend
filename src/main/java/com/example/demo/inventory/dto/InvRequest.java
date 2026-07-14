package com.example.demo.inventory.dto;

public record InvRequest(
        Long product_id,
        Integer stockQuantity,
        Integer reorderLevel
) {
}
