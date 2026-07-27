package com.example.demo.manga.controller;

import com.example.demo.manga.dto.MangaPageResponse;
import com.example.demo.manga.dto.MangaRequest;
import com.example.demo.manga.dto.MangaResponse;
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
    public ResponseEntity<MangaResponse> createProduct(@Valid @RequestBody MangaRequest mangaRequest){
        return new ResponseEntity<>(mangaServices.createManga(mangaRequest), HttpStatus.CREATED);
    }

    @GetMapping("/manga/{manga_id}")
    public MangaResponse getProduct(@PathVariable Long manga_id){
        return mangaServices.getManga(manga_id);
    }

    @GetMapping("/mangas")
    public List<MangaResponse> getAllProducts(){
        return mangaServices.getAllManga();
    }

    @GetMapping("/catalog")
    public MangaPageResponse getMangaPage(
            @RequestParam(name = "page-number", required = false) Integer page_number,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String sortedBy,
            @RequestParam(required = false) String order,
            @RequestParam(required = false) String search
            ){
            return mangaServices.getMangaPage("AVAILABLE", category, page_number, sortedBy, order, search);
    }

    @GetMapping("/pre-order")
    public List<MangaResponse> getPreOrder(){
        return mangaServices.getPreOrder();
    }

    @PutMapping("/manga/{manga_id}")
    public MangaResponse updateProduct(@PathVariable Long manga_id, @RequestBody Manga manga){
        return mangaServices.updateManga(manga_id, manga);
    }

    @DeleteMapping("/manga/{manga_id}")
    public void deleteProduct(@PathVariable Long manga_id){
        mangaServices.deleteManga(manga_id);
     }

}
