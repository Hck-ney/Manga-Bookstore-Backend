package com.example.demo.users.services.interfaces;

import com.example.demo.users.dto.UsersResponse;
import com.example.demo.users.entity.Users;

import java.util.List;

public interface UsersInterface {
    Users createCustomer(Users users);
    UsersResponse getCustomerById(Long id);
    List<Users> getAllCustomers();
    Users updateCustomer(Long id, Users users);
    void deleteCustomer(Long id);
}