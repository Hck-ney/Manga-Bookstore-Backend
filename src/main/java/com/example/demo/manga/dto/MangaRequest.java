package com.example.demo.manga.dto;

import com.example.demo.enums.Availability;
import com.example.demo.enums.Category;
import com.example.demo.manga.entity.Manga;
import com.example.demo.mangaDescription.entity.MangaDescription;
import java.math.BigDecimal;


public record MangaRequest(
        Long id,
        String title,
        String author,
        BigDecimal price,
        Integer publication_year,
        String img_url,
        Category category,
        Availability availability,
        String description
) {
    public static Manga toEntity(MangaRequest mangaRequest, MangaDescription mangaDescription){
        Manga manga = new Manga();
        manga.setTitle(mangaRequest.title);
        manga.setAuthor(mangaRequest.author);
        manga.setPrice(mangaRequest.price);
        manga.setImg_url(mangaRequest.img_url);
        manga.setCategory(mangaRequest.category);
        manga.setPublication_year(mangaRequest.publication_year);
        manga.setMangaDescription(mangaDescription);
        manga.setAvailability(mangaRequest.availability);
        return manga;
    }
}
