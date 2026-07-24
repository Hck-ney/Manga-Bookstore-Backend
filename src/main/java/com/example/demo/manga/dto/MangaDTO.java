package com.example.demo.manga.dto;

import com.example.demo.enums.Availability;
import com.example.demo.enums.Category;
import com.example.demo.manga.entity.Manga;
import com.example.demo.mangaDescription.entity.MangaDescription;

import java.math.BigDecimal;


public record MangaDTO(
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
    public static Manga toEntity(MangaDTO mangaDTO, MangaDescription mangaDescription){
        Manga manga = new Manga();
        manga.setTitle(mangaDTO.title);
        manga.setAuthor(mangaDTO.author);
        manga.setPrice(mangaDTO.price);
        manga.setImg_url(mangaDTO.img_url);
        manga.setCategory(mangaDTO.category);
        manga.setPublication_year(mangaDTO.publication_year);
        manga.setMangaDescription(mangaDescription);
        manga.setAvailability(mangaDTO.availability);
        return manga;
    }
    public static MangaDTO toResponse(Manga manga){
        return new MangaDTO(
                manga.getId(),
                manga.getTitle(),
                manga.getAuthor(),
                manga.getPrice(),
                manga.getPublication_year(),
                manga.getImg_url(),
                manga.getCategory(),
                manga.getAvailability(),
                manga.getMangaDescription().getSynopsis()
        );
    }
}
