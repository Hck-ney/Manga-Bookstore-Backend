package com.example.demo.orders.dto;

import com.example.demo.customer.entity.Customer;
import com.example.demo.enums.status;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record orderResponse(
        Long id,
        Customer customer,
        status status,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
        LocalDateTime date_time
) {
}
