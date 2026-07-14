package com.example.demo.orders.dto;

import java.time.LocalDateTime;

public record orderRequest(
        Long customer_id,
        String status,
        // date is handled by the service layer
        LocalDateTime date_time
) {
}
