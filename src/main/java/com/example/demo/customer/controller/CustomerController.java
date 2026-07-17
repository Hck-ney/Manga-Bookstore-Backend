package com.example.demo.customer.controller;

import com.example.demo.customer.dto.CustomerResponse;
import com.example.demo.customer.entity.Customer;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.customer.services.CustomerService;

import java.util.List;

@RestController
public class CustomerController {
    @Autowired
    private CustomerService cusService;

    // Customer Sign Up
    @PostMapping("/customers")
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer){
        return new ResponseEntity<>(cusService.createCustomer(customer),HttpStatus.CREATED);
    }

    // Fetch all Customers
    @GetMapping("/customers")
    public List<Customer> getCustomer(){
        return cusService.getAllCustomers();
    }

    // Get Specific Customer by ID
    @GetMapping("/customers/{customer_id}")
    public CustomerResponse getCustomerById(@PathVariable Long customer_id){
        return cusService.getCustomerById(customer_id);
    }

    // Update Information of Customer
    @PutMapping("/customers/{customer_id}")
    public Customer updateCustomer(@PathVariable Long customer_id, @RequestBody Customer customer){
        return cusService.updateCustomer(customer_id, customer);
    }

    //Delete Customer
    @DeleteMapping("/customers/{customer_id}")
    public void deleteCustomer(@PathVariable Long customer_id){
        cusService.deleteCustomer(customer_id);
    }
}
