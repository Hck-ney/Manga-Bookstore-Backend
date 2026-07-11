package com.example.demo.dto;

import com.example.demo.entity.Customer;
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
