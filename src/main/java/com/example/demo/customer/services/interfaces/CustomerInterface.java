package com.example.demo.customer.services.interfaces;

import com.example.demo.customer.dto.CustomerResponse;
import com.example.demo.customer.entity.Customer;

import java.util.List;

public interface CustomerInterface {
    Customer createCustomer(Customer customer);
    CustomerResponse getCustomerById(Long id);
    List<Customer> getAllCustomers();
    Customer updateCustomer(Long id, Customer customer);
    void deleteCustomer(Long id);
}