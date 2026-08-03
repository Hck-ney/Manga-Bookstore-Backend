package com.example.demo.cartItem.entity;

import com.example.demo.cart.entity.Cart;
import com.example.demo.manga.entity.Manga;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    private Cart cart;
    @ManyToOne(cascade = CascadeType.ALL)
    private Manga manga;
    private Integer quantity;
    private BigDecimal price;
}
