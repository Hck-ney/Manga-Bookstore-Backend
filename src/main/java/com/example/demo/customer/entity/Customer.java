package com.example.demo.customer.entity;

import com.example.demo.orders.entity.Orders;
import jakarta.persistence.*;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.List;


@Entity
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name must not be blank")
    private String name;
    private String address;
    @Size(min = 9, max = 9, message = "Phone number must be exactly 9 digits")
    @Pattern(regexp = "^09\\d{7}$", message = "Phone number must start with 09 and be 9 digits long")
    @Column(name = "phone_number", length = 9)
    private String phone_number;
    @Email(message = "Basic format invalid")
    @Pattern(regexp = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}$", message = "Strict format invalid")
    private String email;
    @OneToMany(mappedBy = "customer")
    private List<Orders> orders;
}
