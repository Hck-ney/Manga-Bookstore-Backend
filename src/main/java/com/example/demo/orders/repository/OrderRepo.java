package com.example.demo.orders.repository;

import com.example.demo.orders.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Orders,Long> {
}
