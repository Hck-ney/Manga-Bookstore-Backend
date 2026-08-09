package com.example.demo.users.dto;

import com.example.demo.enums.Roles;
import com.example.demo.users.entity.Users;
import com.example.demo.orders.dto.OrderResponse;
import java.util.List;

public record UsersResponse(
        String username,
        String address,
        String phone_number,
        String email,
        List<OrderResponse> ordersList,
        String role,
        Long cart_id
) {
    public static UsersResponse toResponse(Users users){
        return new UsersResponse(
                users.getUsername(),
                users.getAddress(),
                users.getPhone_number(),
                users.getEmail(),
                users.getOrders().stream()
                        .map(OrderResponse::from)
                        .toList(),
                Roles.valueOf(String.valueOf(users.getRole())).toString(),
                users.getCart().getId()
        );
    }
}
