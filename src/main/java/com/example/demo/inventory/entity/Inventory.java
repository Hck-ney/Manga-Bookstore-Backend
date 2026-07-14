package com.example.demo.inventory.entity;

import com.example.demo.product.entity.Product;
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
    private Integer stockQuantity;
    private Integer reorderLevel;
}
