package com.example.demo.users.dto;

import com.example.demo.cart.entity.Cart;
import com.example.demo.enums.Roles;
import com.example.demo.users.entity.Users;

public record UsersRequest(
        String username,
        String address,
        String phone_number,
        String email,
        String password,
        String role
) {
    public static Users toEntity(UsersRequest request, Cart cart, String password){
        Users user = new Users();
        user.setUsername(request.username);
        user.setEmail(request.email);
        user.setAddress(request.address);
        user.setPassword(password);
        user.setPhone_number(request.phone_number);
        user.setRole(Roles.valueOf(request.role));
        user.setCart(cart);
        return user;
    }
}
