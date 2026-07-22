package com.example.demo.inventory.entity;

import com.example.demo.manga.entity.Manga;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data

public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Manga manga;
    private Integer stockQuantity;
    private Integer reorderLevel;
}
