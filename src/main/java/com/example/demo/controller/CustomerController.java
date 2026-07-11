package com.example.demo.controller;

import com.example.demo.entity.Customer;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.services.CustomerService;

import java.util.List;

@RestController
public class CustomerController {
    @Autowired
    private CustomerService cusService;

    // Customer Sign Up
    @PostMapping("/Customer")
    public Customer createCustomer(@Valid @RequestBody Customer request){
        return cusService.createCustomer(request);
    }

    // Fetch all Customers
    @GetMapping("/Customer")
    public List<Customer> getCustomer(){
        return cusService.getAllCustomers();
    }

    // Get Specific Customer by ID
    @GetMapping("/Customer/{id}")
    public Customer getCustomerById(@PathVariable Long id){
        return cusService.getCustomerById(id);
    }

    // Update Information of Customer
    @PutMapping("/Customer/{id}")
    public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer customer){
        return cusService.updateCustomer(id, customer);
    }

    //Delete Customer
}
