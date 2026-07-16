package com.example.demo.orderDetails.dto;

import java.math.BigDecimal;

public record OrderDetailsDTO(
        Long id,
        Long order_id,
        Long product_id,
        Integer quantity,
        BigDecimal total
) {
}
