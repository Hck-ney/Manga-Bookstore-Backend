package com.example.demo.customer.dto;

import com.example.demo.customer.entity.Customer;
import com.example.demo.orders.dto.OrderResponse;
import java.util.List;

public record CustomerResponse(
        String name,
        String address,
        String phone_number,
        String email,
        List<OrderResponse> ordersList
) {
    public static CustomerResponse from(Customer customer){
        return new CustomerResponse(
                customer.getName(),
                customer.getAddress(),
                customer.getPhone_number(),
                customer.getEmail(),
                customer.getOrders().stream()
                        .map(OrderResponse::from)
                        .toList()
        );
    }
}
