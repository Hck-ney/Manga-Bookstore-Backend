package com.example.demo.entity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NonNull;


import java.math.BigDecimal;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(unique = true)
    private String sku;
}
