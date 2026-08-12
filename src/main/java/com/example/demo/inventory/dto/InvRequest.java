package com.example.demo.inventory.dto;

import com.example.demo.enums.Availability;
import com.example.demo.enums.Category;

import java.math.BigDecimal;

public record InvRequest(
        String title,
        String author,
        BigDecimal price,
        Integer publication_year,
        String img_url,
        Category category,
        Availability availability,
        String description,
        Integer stockQuantity,
        Integer reOrderLevel
) {
}
