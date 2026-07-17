package com.example.demo.orders.dto;

import com.example.demo.orderDetails.dto.OrderDetailsDTO;

import java.time.LocalDateTime;
import java.util.List;

public record orderRequest(
        Long customer_id,
        String status,
        // date is handled by the service layer
        LocalDateTime date_time,

        List<OrderDetailsDTO> orderDetailsDTO

) {
}
