package com.example.demo.users.controller;

import com.example.demo.users.dto.UsersRequest;
import com.example.demo.users.dto.UsersResponse;
import com.example.demo.users.entity.Users;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.users.services.UsersService;

import java.util.List;

@RestController
public class UsersController {
    @Autowired
    private UsersService cusService;

    // Customer Sign Up
    @PostMapping("/users")
    public ResponseEntity<UsersResponse> createCustomer(@Valid @RequestBody UsersRequest request){
        return new ResponseEntity<>(cusService.createUser(request),HttpStatus.CREATED);
    }

    @GetMapping("/users")
    public List<Users> getCustomer(){
        return cusService.getAllUser();
    }

    @GetMapping("/users/{username}")
    public UsersResponse getCustomerById(@Valid @PathVariable String username){
        return cusService.getUserByUsername(username);
    }

    // Update Information of Customer
    @PutMapping("/users/{customer_id}")
    public Users updateCustomer(@Valid @PathVariable Long user_id, @RequestBody Users users){
        return cusService.updateUser(user_id, users);
    }

    //Delete Customer
    @DeleteMapping("/users/{user_id}")
    public void deleteCustomer(@Valid @PathVariable Long user_id){
        cusService.deleteUser(user_id);
    }
}
