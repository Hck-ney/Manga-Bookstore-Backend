package com.example.demo.manga.services;

import com.example.demo.enums.Availability;
import com.example.demo.enums.Category;
import com.example.demo.exceptions.OrderException;
import com.example.demo.external.supabase.SupabaseStorageService;
import com.example.demo.inventory.dto.InvRequest;
import com.example.demo.inventory.entity.Inventory;
import com.example.demo.inventory.repository.InventoryRepo;
import com.example.demo.manga.dto.MangaPageResponse;
import com.example.demo.manga.dto.MangaRequest;
import com.example.demo.manga.dto.MangaResponse;
import com.example.demo.manga.entity.Manga;
import com.example.demo.manga.repository.MangaRepository;
import com.example.demo.mangaDescription.entity.MangaDescription;
import jakarta.persistence.criteria.Predicate;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class MangaServices {

    @Autowired
    private MangaRepository mangaRepository;

    @Autowired
    private InventoryRepo inventoryRepo;

    @Autowired
    private SupabaseStorageService storageService;

    LocalDateTime date = LocalDateTime.now();

    @Transactional
    public MangaResponse createManga(InvRequest invRequest, MultipartFile image) throws IOException {
        if (invRequest.price() == null || invRequest.category() == null
                || invRequest.publication_year() == null || invRequest.description() == null ||
                invRequest.description().isBlank() || invRequest.author() == null
                || invRequest.img_url() == null || invRequest.title() == null ||
                invRequest.stockQuantity() == null || invRequest.reOrderLevel() == null) {
            throw new OrderException("Missing required fields", HttpStatus.BAD_REQUEST);
        }
        MangaDescription desc = new MangaDescription();
        Manga manga = MangaRequest.toEntity(invRequest, desc);
        manga.setImg_url(storageService.upload(image));
        if (invRequest.availability() == Availability.AVAILABLE) {
            manga.setDateAdded(date);
        }
        desc.setManga(manga);
        desc.setSynopsis(invRequest.description());
        Inventory inventory = new Inventory();
        inventory.setManga(manga);
        inventory.setReorderLevel(invRequest.reOrderLevel());
        inventory.setStockQuantity(invRequest.stockQuantity());
        inventoryRepo.save(inventory);
        mangaRepository.save(manga);
        return MangaResponse.toResponse(manga);
    }

    public MangaResponse getManga(Long manga_id) {
        Manga manga = mangaRepository.findById(manga_id).orElseThrow(() -> new OrderException("Manga associated with this Id is not found", HttpStatus.NOT_FOUND));
        return MangaResponse.toResponse(manga);
    }

    // GET AVAILABLE MANGA IN PAGES
    public MangaPageResponse getMangaPage(String availability, String category, Integer page_number, String sortedBy, String order, String search) {
        if (page_number == null || page_number <= 1) {
            page_number = 0;
        } else {
            page_number = page_number - 1;
        }
        Sort.Direction direction;
        if (Objects.equals(order, "desc")) {
            direction = Sort.Direction.DESC;
        } else {
            direction = Sort.Direction.ASC;
        }
        String sortField = (sortedBy != null && !sortedBy.isEmpty()) ? sortedBy : "dateAdded";
        Pageable pageable = PageRequest.of(page_number, 10, Sort.by(direction, sortField));
        if (search != null) {
            Pageable page = PageRequest.of(page_number, 10, Sort.by(Sort.Direction.DESC, "title"));
            Specification<Manga> spec = (root, query, criteriaBuilder) -> {
                List<Predicate> predicates = new ArrayList<>();
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), "%" + search.toLowerCase() + "%"));
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("author")), "%" + search.toLowerCase() + "%"));
                return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
            };
            Page<Manga> result = mangaRepository.findAll(spec, page);
            List<MangaResponse> content = result.getContent().stream()
                    .map(MangaResponse::toResponse)
                    .toList();
            return new MangaPageResponse(
                    content,
                    result.getTotalPages()
            );
        } else {
            Specification<Manga> spec = (root, query, criteriaBuilder) -> {
                List<Predicate> predicates = new ArrayList<>();
                if (availability != null && !availability.isEmpty()) {
                    predicates.add(criteriaBuilder.equal(root.get("availability"), Availability.valueOf(availability)));
                } else {
                    predicates.add(criteriaBuilder.equal(root.get("availability"), Availability.AVAILABLE));
                }
                if (category != null && !category.isEmpty()) {
                    predicates.add(criteriaBuilder.equal(root.get("category"), Category.valueOf(category)));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            };
            Page<Manga> resultPage = mangaRepository.findAll(spec, pageable);
            List<MangaResponse> content = resultPage.getContent().stream()
                    .map(MangaResponse::toResponse)
                    .toList();
            return new MangaPageResponse(
                    content,
                    resultPage.getTotalPages()
            );
        }

    }

    // GET ALL MANGA FOR TESTING
    public List<MangaResponse> getAllManga() {
        List<Manga> mangaList = mangaRepository.findAll();
        List<MangaResponse> dtoList = new ArrayList<>();
        for (Manga manga : mangaList) {
            MangaResponse dto = MangaResponse.toResponse(manga);
            dtoList.add(dto);
        }
        return dtoList;
    }

    // ADMIN: GET ALL MANGA IN PAGES
    public MangaPageResponse getAllMangaPages(Integer page_number){
        if(page_number==null){
            page_number = 0;
        }
        Pageable page = PageRequest.of(page_number, 5, Sort.by(Sort.Direction.DESC, "dateAdded"));
        Page<Manga> result = mangaRepository.findAll(page);
        List<MangaResponse> content = result.getContent().stream()
                .map(MangaResponse::toResponse)
                .toList();
        return new MangaPageResponse(
                content,
                result.getTotalPages()
        );
    }

    public List<MangaResponse> getPreOrder(){
        Pageable pageable = PageRequest.of(0, 10, Sort.by("dateAdded"));
        Page<Manga> manga = mangaRepository.findByAvailability(Availability.PRE_ORDER, pageable);
        return manga.getContent().stream()
                .map(MangaResponse::toResponse)
                .collect(Collectors.toList());
    }

    public MangaResponse updateManga(Long manga_id, Manga manga) {
        Manga existing = mangaRepository.findById(manga_id).orElseThrow(() -> new OrderException("Manga associated with this Id is not found", HttpStatus.NOT_FOUND));
        if (manga == null) {
            throw new OrderException("Request has empty body", HttpStatus.BAD_REQUEST);
        }
        if (manga.getTitle() != null) {
            existing.setTitle(manga.getTitle());
        }
        if (manga.getAuthor() != null) {
            existing.setAuthor(manga.getAuthor());
        }
        if (manga.getPublication_year() != null) {
            existing.setPublication_year(manga.getPublication_year());
        }
        if (manga.getPrice() != null) {
            existing.setPrice(manga.getPrice());
        }
        if (manga.getImg_url() != null) {
            existing.setImg_url(manga.getImg_url());
        }
        if (manga.getCategory() != null) {
            existing.setCategory(manga.getCategory());
        }
        if (manga.getAvailability() != null) {
            existing.setAvailability(manga.getAvailability());
        }
        Manga updatedManga = mangaRepository.save(existing);
        return MangaResponse.toResponse(updatedManga);
    }

    public void deleteManga(Long manga_id) {
        Manga manga = mangaRepository.findById(manga_id).orElseThrow(() -> new OrderException("Manga associated with this Id is not found", HttpStatus.NOT_FOUND));
        mangaRepository.delete(manga);
    }
}