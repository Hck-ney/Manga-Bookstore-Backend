package com.example.demo.dto;

import com.example.demo.entity.Customer;
import com.example.demo.enums.status;

import java.time.LocalDateTime;

public record orderResponse(
        Long id,
        Customer customer_id,
        status status,
        LocalDateTime date_time
) {
}
