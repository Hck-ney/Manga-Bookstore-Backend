package com.example.demo.orderDetails.repository;

import com.example.demo.orderDetails.dto.BestSellerDTO;
import com.example.demo.orderDetails.entity.OrderDetails;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailsRepo extends JpaRepository<OrderDetails,Long> {
    @Query("""
        SELECT new com.example.demo.orderDetails.dto.BestSellerDTO(
            m.id,
            m.title,
            m.img_url,
            SUM(od.quantity),
            SUM(od.total)
        )
        FROM OrderDetails od
        JOIN od.manga m
        JOIN od.order o
        WHERE o.status NOT IN ('CANCELLED', 'REFUNDED')
        GROUP BY m.id, m.title, m.img_url
        ORDER BY SUM(od.quantity) DESC
    """)
    List<BestSellerDTO> findTopBestSeller(Pageable pageable);
}
