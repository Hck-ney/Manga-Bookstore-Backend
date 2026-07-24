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

    @GetMapping("/catalog/{page_number}")
    public List<MangaDTO> getMangaPage(
            @PathVariable Integer page_number,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String sortedBy
            ){
        return mangaServices.getMangaPage("AVAILABLE", category, page_number, sortedBy);
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
