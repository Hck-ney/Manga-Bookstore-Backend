package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data

public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Product product;
    private int  stockQuantity;
    private int reorderLevel;
}
