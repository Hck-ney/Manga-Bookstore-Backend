package com.example.demo.inventory.dto;

import com.example.demo.manga.dto.MangaResponse;

import java.util.List;

public record InvPageResponse(
        List<InvResponse> manga,
        int totalPages
) {
}
