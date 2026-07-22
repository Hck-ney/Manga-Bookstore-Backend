package com.example.demo.genre.controller;

import com.example.demo.genre.entity.Genre;
import com.example.demo.genre.services.GenreServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GenreController {

    @Autowired
    GenreServices genreServices;

    @PostMapping("/genre")
    public Genre createGenre(Genre genre){
        return genreServices.createGenre(genre);
    }

    @GetMapping("/genres")
    public List<Genre> getAllGenre(){
        return genreServices.getAllGenre();
    }

    @PutMapping("/genre/{genre_id}")
    public Genre updateGenre(Long id, Genre genre){
        return genreServices.updateGenre(id, genre);
    }

    @DeleteMapping("/genre/{genre_id}")
    public void deleteGenre(Long id){
        genreServices.deleteGenre(id);
    }
}
