package com.example.demo.users.services.interfaces;

import com.example.demo.users.dto.UsersRequest;
import com.example.demo.users.dto.UsersResponse;
import com.example.demo.users.entity.Users;

import java.util.List;

public interface UsersInterface {
    UsersResponse createUser(UsersRequest request);
    UsersResponse getUserByUsername(String username);
    List<Users> getAllUser();
    Users updateUser(Long id, Users users);
    void deleteUser(Long id);
}