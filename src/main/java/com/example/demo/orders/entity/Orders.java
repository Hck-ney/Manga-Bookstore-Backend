package com.example.demo.orders.entity;

import com.example.demo.customer.entity.Customer;
import com.example.demo.orderDetails.entity.OrderDetails;
import jakarta.persistence.*;
import lombok.Data;
import com.example.demo.enums.status;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    private BigDecimal total;
    private LocalDateTime date_time;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderDetails> orderDetails = new ArrayList<>();
}
