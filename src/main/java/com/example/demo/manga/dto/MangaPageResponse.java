package com.example.demo.manga.dto;

import java.util.List;

public record MangaPageResponse(
        List<MangaResponse> manga,
        int totalPages
) {
}
