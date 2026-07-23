package com.example.demo.mangaDescription.controller;

import com.example.demo.mangaDescription.entity.MangaDescription;
import com.example.demo.mangaDescription.services.MangaDescriptionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MangaDescriptionController {

    @Autowired
    MangaDescriptionServices mangaDescriptionServices;

    @GetMapping("/manga_description/{id}")
    public MangaDescription getMangaDescription(Long id){
        return mangaDescriptionServices.getDescription(id);
    }

    @PutMapping("/manga_description/{id}")
    public MangaDescription updateMangaDescription(Long id, MangaDescription mangaDescription){
        return mangaDescriptionServices.updateDescription(id, mangaDescription);
    }
}

