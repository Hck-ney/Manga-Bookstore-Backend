package com.example.demo.orders.dto;

import com.example.demo.customer.entity.Customer;
import com.example.demo.enums.status;

import java.time.LocalDateTime;

public record orderResponse(
        Long id,
        Customer customer,
        status status,
        LocalDateTime date_time
) {
}
