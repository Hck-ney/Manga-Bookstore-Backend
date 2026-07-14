package com.example.demo.customer.dto;

import com.example.demo.customer.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class customerMapper {
    public customerDTO mapCustomerDTO (Customer customer){
        return new customerDTO(
                customer.getId(),
                customer.getName(),
                customer.getEmail()
        );
    }
}
