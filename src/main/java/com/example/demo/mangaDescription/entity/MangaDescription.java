package com.example.demo.mangaDescription.entity;

import com.example.demo.manga.entity.Manga;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class MangaDescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "TEXT")
    private String synopsis;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "manga_id")
    private Manga manga;
}
