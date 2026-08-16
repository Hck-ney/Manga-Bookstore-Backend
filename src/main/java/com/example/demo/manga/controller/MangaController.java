package com.example.demo.manga.controller;

import com.example.demo.inventory.dto.InvRequest;
import com.example.demo.manga.dto.MangaPageResponse;
import com.example.demo.manga.dto.MangaResponse;
import com.example.demo.manga.entity.Manga;
import com.example.demo.manga.services.MangaServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class MangaController {

    @Autowired
    private MangaServices mangaServices;

    @PostMapping(value = "/admin/inventory",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<MangaResponse> createProduct(@Valid @RequestPart("data") InvRequest invRequest,
                                                       @RequestPart("image") MultipartFile image) throws IOException {
        return new ResponseEntity<>(mangaServices.createManga(invRequest, image), HttpStatus.CREATED);
    }

    @GetMapping("/manga/{manga_id}")
    public MangaResponse getProduct(@PathVariable Long manga_id){
        return mangaServices.getManga(manga_id);
    }

    @GetMapping("/mangas")
    public List<MangaResponse> getAllProducts(){
        return mangaServices.getAllManga();
    }

    // ADMIN: GET ALL MANGA
    @GetMapping("/admin/manga")
    public MangaPageResponse getAllManga(@Valid @RequestParam(name = "page", required = false) Integer page_number){
        return mangaServices.getAllMangaPages(page_number);
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

    @GetMapping("/new-arrival")
    public MangaPageResponse getNewArrival(){
        return mangaServices.getPreArrivalSection();
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
