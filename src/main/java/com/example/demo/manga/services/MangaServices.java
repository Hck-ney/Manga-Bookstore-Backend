package com.example.demo.manga.services;

import com.example.demo.enums.Availability;
import com.example.demo.enums.Category;
import com.example.demo.exceptions.OrderException;
import com.example.demo.manga.dto.MangaDTO;
import com.example.demo.manga.entity.Manga;
import com.example.demo.manga.repository.MangaRepository;
import com.example.demo.mangaDescription.entity.MangaDescription;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
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

    public List<MangaDTO> getMangaPage(String availability, String category, Integer page_number, String sortedBy) {

        if (page_number == null || page_number <= 1) {
            page_number = 0;
        } else {
            page_number = page_number - 1;
        }
        String sortField = (sortedBy != null && !sortedBy.isEmpty()) ? sortedBy : "title";
        Pageable pageable = PageRequest.of(page_number, 10, Sort.by(sortField));
        Specification<Manga> spec = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (availability != null && !availability.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("availability"), Availability.valueOf(availability)));
            }
            else {
                predicates.add(criteriaBuilder.equal(root.get("availability"), Availability.AVAILABLE));
            }
            if (category != null && !category.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("category"), Category.valueOf(category)));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
        Page<Manga> resultPage = mangaRepository.findAll(spec, pageable);
        return resultPage.getContent().stream()
                .map(MangaDTO::toResponse)
                .toList();
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
        if(manga.getAvailability()!=null){
            existing.setAvailability(manga.getAvailability());
        }
        Manga updatedManga = mangaRepository.save(existing);
        return MangaDTO.toResponse(updatedManga);
    }

    public void deleteManga(Long  manga_id){
        Manga manga = mangaRepository.findById(manga_id).orElseThrow(()-> new OrderException("Manga associated with this Id is not found", HttpStatus.NOT_FOUND));
        mangaRepository.delete(manga);
    }
}
