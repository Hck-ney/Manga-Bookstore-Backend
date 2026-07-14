package com.example.demo.orderDetails.repository;

import com.example.demo.orderDetails.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailsRepo extends JpaRepository<OrderDetails,Long> {
}
