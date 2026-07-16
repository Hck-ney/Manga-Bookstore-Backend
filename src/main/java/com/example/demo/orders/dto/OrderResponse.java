package com.example.demo.orders.dto;

import com.example.demo.enums.status;
import com.example.demo.orderDetails.dto.OrderDetailsResponse;
import com.example.demo.orders.entity.Orders;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderResponse(
        Long id,
        Long customer_id,
        status status,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
        LocalDateTime date_time,
        BigDecimal total,
        List<OrderDetailsResponse> orderDetailsList
) {
        public static OrderResponse from(Orders orders){
                return new OrderResponse(
                        orders.getId(),
                        orders.getCustomer().getId(),
                        orders.getStatus(),
                        orders.getDate_time(),
                        orders.getTotal(),
                        orders.getOrderDetails().stream()
                                .map(OrderDetailsResponse::from)
                                .toList()
                );
        }
}
