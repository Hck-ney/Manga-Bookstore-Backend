package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders orders_id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private Integer price;
    private Integer quantity;
    private Integer total;
}
