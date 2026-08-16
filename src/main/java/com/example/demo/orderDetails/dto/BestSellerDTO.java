package com.example.demo.orderDetails.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class BestSellerDTO {
    private Long id;
    private String title;
    private String imgUrl;
    private Long totalSold;
    private BigDecimal totalRevenue;
}