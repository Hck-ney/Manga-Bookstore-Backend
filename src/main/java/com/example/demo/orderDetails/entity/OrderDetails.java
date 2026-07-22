package com.example.demo.orderDetails.entity;

import com.example.demo.manga.entity.Manga;
import com.example.demo.orders.entity.Orders;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders order;

    @ManyToOne
    @JoinColumn(name = "manga_id")
    private Manga manga;
    private BigDecimal price;
    private Integer quantity;
    private BigDecimal total;
}
