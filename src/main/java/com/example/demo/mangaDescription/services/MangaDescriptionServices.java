package com.example.demo.mangaDescription.services;

import com.example.demo.exceptions.OrderException;
import com.example.demo.mangaDescription.entity.MangaDescription;
import com.example.demo.mangaDescription.repository.MangaDescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Service
public class MangaDescriptionServices {

    @Autowired
    MangaDescriptionRepository mangaDescriptionRepository;
    // manga description creation should be together with Manga Creation

    public MangaDescription getDescription(Long id){
        return mangaDescriptionRepository.findById(id).orElseThrow(()-> new OrderException("Description associated with this ID cannot be found", HttpStatus.NOT_FOUND));
    }

    public MangaDescription updateDescription(Long id, MangaDescription mangaDescription){
        MangaDescription existing = mangaDescriptionRepository.findById(id).orElseThrow(()-> new OrderException("Description associated with this ID cannot be found", HttpStatus.NOT_FOUND));
        if(mangaDescription.getSynopsis()!= null){
            existing.setSynopsis(mangaDescription.getSynopsis());
        }
        return mangaDescriptionRepository.save(existing);
    }
}
