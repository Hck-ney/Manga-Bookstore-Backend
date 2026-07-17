package com.example.demo.orders.entity;

import com.example.demo.customer.entity.Customer;
import com.example.demo.orderDetails.entity.OrderDetails;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import com.example.demo.enums.status;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Customer customer;
    private String customer_name;
    private String address;
    private Integer phone_number;
    @Email(message = "Basic format invalid")
    @Pattern(regexp = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}$", message = "Strict format invalid")
    private String email;
    @Enumerated(EnumType.STRING)
    private status status;
    private BigDecimal total;
    private LocalDateTime date_time;
    @OneToMany(mappedBy = "order", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    private List<OrderDetails> orderDetails = new ArrayList<>();
}
