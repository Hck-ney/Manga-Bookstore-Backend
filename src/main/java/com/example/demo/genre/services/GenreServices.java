package com.example.demo.genre.services;

import com.example.demo.exceptions.OrderException;
import com.example.demo.genre.entity.Genre;
import com.example.demo.genre.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServices {

    @Autowired
    GenreRepository genreRepository;

    public Genre createGenre(Genre genre){
        return genreRepository.save(genre);
    }

    public List<Genre> getAllGenre(){
        return genreRepository.findAll();
    }

    public Genre updateGenre(Long id, Genre genre){
        Genre existingGenre = genreRepository.findById(id).orElseThrow(()-> new OrderException("Genre associated with this ID cannot be found", HttpStatus.NOT_FOUND));
        existingGenre.setName(genre.getName());
        return genreRepository.save(existingGenre);
    }

    public void deleteGenre(Long id){
        Genre existingGenre = genreRepository.findById(id).orElseThrow(()-> new OrderException("Genre associated with this ID cannot be found", HttpStatus.NOT_FOUND));
        genreRepository.delete(existingGenre);
    }
}
