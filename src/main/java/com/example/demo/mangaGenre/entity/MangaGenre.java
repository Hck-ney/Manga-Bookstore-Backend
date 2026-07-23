package com.example.demo.mangaGenre.entity;

import com.example.demo.manga.entity.Manga;
import com.example.demo.genre.entity.Genre;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class MangaGenre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "manga_id")
    private Manga manga;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "genre_id")
    private Genre genre;
}
