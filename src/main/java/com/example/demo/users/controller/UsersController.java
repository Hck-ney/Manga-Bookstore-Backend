package com.example.demo.users.controller;

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
    @PostMapping("/user")
    public ResponseEntity<Users> createCustomer(@Valid @RequestBody Users users){
        return new ResponseEntity<>(cusService.createCustomer(users),HttpStatus.CREATED);
    }

    // Fetch all Customers
    @GetMapping("/user")
    public List<Users> getCustomer(){
        return cusService.getAllCustomers();
    }

    // Get Specific Customer by ID
    @GetMapping("/user/{customer_id}")
    public UsersResponse getCustomerById(@PathVariable Long user_id){
        return cusService.getCustomerById(user_id);
    }

    // Update Information of Customer
    @PutMapping("/user/{customer_id}")
    public Users updateCustomer(@PathVariable Long user_id, @RequestBody Users users){
        return cusService.updateCustomer(user_id, users);
    }

    //Delete Customer
    @DeleteMapping("/user/{user_id}")
    public void deleteCustomer(@PathVariable Long user_id){
        cusService.deleteCustomer(user_id);
    }
}
