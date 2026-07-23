package com.example.demo.manga.controller;

import com.example.demo.manga.dto.MangaDTO;
import com.example.demo.manga.entity.Manga;
import com.example.demo.manga.services.MangaServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MangaController {

    @Autowired
    private MangaServices mangaServices;

    @PostMapping("/manga")
    public ResponseEntity<MangaDTO> createProduct(@Valid @RequestBody MangaDTO mangaDTO){
        return new ResponseEntity<>(mangaServices.createManga(mangaDTO), HttpStatus.CREATED);
    }

    @GetMapping("/manga/{manga_id}")
    public MangaDTO getProduct(@PathVariable Long manga_id){
        return mangaServices.getManga(manga_id);
    }

    @GetMapping("/mangas")
    public List<MangaDTO> getAllProducts(){
        return mangaServices.getAllManga();
    }

    @GetMapping("/catalog/1")
    public List<MangaDTO> getMangaPage1(){
        return mangaServices.mangaPage1();
    }

    @GetMapping("/catalog/2")
    public List<MangaDTO> getMangaPage2(){
        return mangaServices.mangaPage2();
    }

    @PutMapping("/manga/{manga_id}")
    public MangaDTO updateProduct(@PathVariable Long manga_id, @RequestBody Manga manga){
        return mangaServices.updateManga(manga_id, manga);
    }

    @DeleteMapping("/manga/{manga_id}")
    public void deleteProduct(@PathVariable Long manga_id){
        mangaServices.deleteManga(manga_id);
     }
}
