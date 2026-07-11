package com.example.demo.services.interfaces;

import com.example.demo.entity.Customer;

import java.util.List;

public interface CustomerInterface {
    Customer createCustomer(Customer customer);
    Customer getCustomerById(Long id);
    List<Customer> getAllCustomers();
    Customer updateCustomer(Long id, Customer customer);
    void deleteCustomer(Long id);
}