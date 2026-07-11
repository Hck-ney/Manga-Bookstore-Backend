package com.example.demo.dto;

import com.example.demo.entity.Customer;
import com.example.demo.enums.status;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

public record orderRequest(
        Long customer_id,
        String status,
        LocalDateTime date
) {
}
