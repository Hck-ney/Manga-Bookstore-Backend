package com.example.demo.manga.entity;
import com.example.demo.enums.Availability;
import com.example.demo.enums.Category;
import com.example.demo.mangaDescription.entity.MangaDescription;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;


import java.math.BigDecimal;
import java.time.LocalDateTime;

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
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSS")
    private LocalDateTime dateAdded;
    @OneToOne(cascade = CascadeType.ALL)
    private MangaDescription mangaDescription;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Availability availability;
}
