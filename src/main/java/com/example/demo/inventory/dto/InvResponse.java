package com.example.demo.inventory.dto;

public record InvResponse(
    Long product_id,
    Integer stockQuantity,
    Integer reorderLevel
) {
}
