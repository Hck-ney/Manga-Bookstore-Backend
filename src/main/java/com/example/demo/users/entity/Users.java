package com.example.demo.users.entity;

import com.example.demo.cart.entity.Cart;
import com.example.demo.enums.Roles;
import com.example.demo.orders.entity.Orders;
import jakarta.persistence.*;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Entity
@Data
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Name must not be null")
    private String username;
    private String address;
    @Size(min = 11, max = 11, message = "Phone number must be exactly 9 digits")
    @Pattern(regexp = "^09\\d{9}$", message = "Phone number must start with 09 and be 11 digits long")
    @Column(name = "phone_number", length = 11)
    private String phone_number;
    @Column(unique = true)
    @Email(message = "Basic format invalid")
    @NotNull(message = "Email must not be null")
    @Pattern(regexp = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}$", message = "Strict format invalid")
    private String email;
    private String password;
    @OneToMany(mappedBy = "users")
    private List<Orders> orders = new ArrayList<>();
    @Enumerated(EnumType.STRING)
    private Roles role;
    @OneToOne(cascade = CascadeType.ALL)
    private Cart cart;
}
