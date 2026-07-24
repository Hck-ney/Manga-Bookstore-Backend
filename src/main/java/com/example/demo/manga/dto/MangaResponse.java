package com.example.demo.manga.dto;

import com.example.demo.enums.Availability;
import com.example.demo.enums.Category;
import com.example.demo.manga.entity.Manga;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record MangaResponse(
        Long id,
        String title,
        String author,
        BigDecimal price,
        Integer publication_year,
        String img_url,
        Category category,
        Availability availability,
        String description,
        LocalDateTime date_added
) {
    public static MangaResponse toResponse(Manga manga){
        return new MangaResponse(
                manga.getId(),
                manga.getTitle(),
                manga.getAuthor(),
                manga.getPrice(),
                manga.getPublication_year(),
                manga.getImg_url(),
                manga.getCategory(),
                manga.getAvailability(),
                manga.getMangaDescription().getSynopsis(),
                manga.getDateAdded()
        );
    }
}
