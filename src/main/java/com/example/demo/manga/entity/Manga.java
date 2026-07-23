package com.example.demo.manga.entity;
import com.example.demo.enums.Category;
import com.example.demo.mangaDescription.entity.MangaDescription;
import jakarta.persistence.*;
import lombok.Data;


import java.math.BigDecimal;

@Entity
@Data
public class Manga {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String title;
    private String author;
    @Column(nullable = false)
    private BigDecimal price;
    private Integer publication_year;
    @Column(columnDefinition = "TEXT")
    private String img_url;
    private Category category;
    @OneToOne(cascade = CascadeType.ALL)
    private MangaDescription mangaDescription;
}
