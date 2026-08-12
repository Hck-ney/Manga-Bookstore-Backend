package com.example.demo.inventory.dto;

import com.example.demo.inventory.entity.Inventory;
import com.example.demo.manga.dto.MangaResponse;

public record InvResponse(
    MangaResponse manga,
    Integer stockQuantity,
    Integer reorderLevel
) {
    public static InvResponse toResponse(Inventory inventory){
        return new InvResponse(
                MangaResponse.toResponse(inventory.getManga()),
                inventory.getStockQuantity(),
                inventory.getReorderLevel()
        );
    }
}
