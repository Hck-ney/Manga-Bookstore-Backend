package com.example.demo.manga.services;

import com.example.demo.exceptions.OrderException;
import com.example.demo.manga.dto.MangaDTO;
import com.example.demo.manga.entity.Manga;
import com.example.demo.manga.repository.MangaRepository;
import com.example.demo.mangaDescription.entity.MangaDescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MangaServices {

    @Autowired
    private MangaRepository mangaRepository;

    public MangaDTO createManga(MangaDTO mangaDTO){
        if(mangaDTO.price()==null || mangaDTO.category()==null
                || mangaDTO.publication_year()==null || mangaDTO.description()==null || mangaDTO.author()==null
                || mangaDTO.img_url()== null || mangaDTO.title() == null ){
            throw new OrderException("Missing required fields", HttpStatus.BAD_REQUEST);
        }
        MangaDescription desc = new MangaDescription();
        Manga manga = MangaDTO.toEntity(mangaDTO, desc);
        desc.setManga(manga);
        desc.setSynopsis(mangaDTO.description());
        mangaRepository.save(manga);
        return MangaDTO.toResponse(manga);
    }

    public MangaDTO getManga(Long manga_id){
        Manga manga =  mangaRepository.findById(manga_id).orElseThrow(()-> new OrderException("Manga associated with this Id is not found", HttpStatus.NOT_FOUND));
        return MangaDTO.toResponse(manga);
    }

    public List<MangaDTO> mangaPage1(){
        Pageable first10 = PageRequest.of(0, 10, Sort.by("title"));
        List<MangaDTO> dtoPage1 = new ArrayList<>();
        List<Manga> page1 = mangaRepository.findAll(first10).getContent();
        for(Manga x: page1){
            dtoPage1.add(MangaDTO.toResponse(x));
        }
        return dtoPage1;
    }

    public List<MangaDTO> mangaPage2(){
        Pageable second10 = PageRequest.of(1,10, Sort.by("title"));
        List<MangaDTO> dtoPage2 = new ArrayList<>();
        List<Manga> page2 = mangaRepository.findAll(second10).getContent();
        for(Manga x: page2){
            dtoPage2.add(MangaDTO.toResponse(x));
        }
        return dtoPage2;
    }

    public List<MangaDTO> getAllManga(){
        List<Manga> mangaList = mangaRepository.findAll();
        List<MangaDTO> dtoList = new ArrayList<>();
        for(Manga manga: mangaList ){
            MangaDTO dto = MangaDTO.toResponse(manga);
            dtoList.add(dto);
        }
        return dtoList;
    }

    public MangaDTO updateManga(Long manga_id, Manga manga){
        Manga existing = mangaRepository.findById(manga_id).orElseThrow(()-> new OrderException("Manga associated with this Id is not found", HttpStatus.NOT_FOUND));
        if(manga==null){
            throw new OrderException("Request has empty body",HttpStatus.BAD_REQUEST);
        }
        if(manga.getTitle()!=null){
            existing.setTitle(manga.getTitle());
        }
        if(manga.getAuthor()!=null){
            existing.setAuthor(manga.getAuthor());
        }
        if(manga.getPublication_year()!=null){
            existing.setPublication_year(manga.getPublication_year());
        }
        if(manga.getPrice()!=null){
            existing.setPrice(manga.getPrice());
        }
        if(manga.getImg_url()!=null){
            existing.setImg_url(manga.getImg_url());
        }
        if(manga.getCategory()!= null){
            existing.setCategory(manga.getCategory());
        }
        Manga updatedManga = mangaRepository.save(existing);
        return MangaDTO.toResponse(updatedManga);
    }

    public void deleteManga(Long  manga_id){
        Manga manga = mangaRepository.findById(manga_id).orElseThrow(()-> new OrderException("Manga associated with this Id is not found", HttpStatus.NOT_FOUND));
        mangaRepository.delete(manga);
    }
}
