package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import com.example.demo.enums.status;

import java.time.LocalDateTime;

@Entity
@Data
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;
    @Enumerated(EnumType.STRING)
    private status status;
    private LocalDateTime date_time;
}
