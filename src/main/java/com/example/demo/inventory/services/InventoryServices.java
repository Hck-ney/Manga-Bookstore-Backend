package com.example.demo.inventory.services;

import com.example.demo.exceptions.OrderException;
import com.example.demo.external.supabase.SupabaseStorageService;
import com.example.demo.inventory.dto.InvPageResponse;
import com.example.demo.inventory.dto.InvRequest;
import com.example.demo.inventory.dto.InvResponse;
import com.example.demo.inventory.entity.Inventory;
import com.example.demo.inventory.repository.InventoryRepo;
import com.example.demo.manga.entity.Manga;
import com.example.demo.manga.repository.MangaRepository;
import com.example.demo.mangaDescription.entity.MangaDescription;
import com.example.demo.mangaDescription.repository.MangaDescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class InventoryServices {

    @Autowired
    private InventoryRepo inventoryRepo;

    @Autowired
    private MangaRepository mangaRepository;

    @Autowired
    private MangaDescriptionRepository mangaDescriptionRepository;

    @Autowired
    private SupabaseStorageService storageService;

//    public Inventory addInventory(InvRequest invRequest){
//        if(invRequest.product_id() == null || invRequest.reorderLevel() == null || invRequest.stockQuantity() == null){
//            throw new OrderException("Missing required field", HttpStatus.BAD_REQUEST);
//        }
//        Manga manga = mangaRepository.findById(invRequest.product_id()).orElseThrow(()-> new OrderException("Product associated with this ID cannot be found", HttpStatus.NOT_FOUND));
//        Inventory newInv = new Inventory();
//        newInv.setManga(manga);
//        newInv.setStockQuantity(invRequest.stockQuantity());
//        newInv.setReorderLevel(invRequest.reorderLevel());
//        return inventoryRepo.save(newInv);
//    }
//
//    public List<Inventory> getInventoryList(){
//        return inventoryRepo.findAll();
//    }
//
//    public Inventory getInventory(Long inventory_id){
//        return inventoryRepo.findById(inventory_id).orElseThrow(()-> new OrderException("Row associated with this ID cannot be found", HttpStatus.NOT_FOUND));
//    }
//
//    public Inventory updateInventory(Long inventory_id, InvRequest invRequest){
//        Inventory newInv = inventoryRepo.findById(inventory_id).orElseThrow(()-> new OrderException("Row with this associated ID cannot be found", HttpStatus.NOT_FOUND));
//        Manga manga = mangaRepository.findById(invRequest.product_id()).orElseThrow(()-> new OrderException("Product with this associated ID cannot be found", HttpStatus.NOT_FOUND));
//        newInv.setManga(manga);
//        newInv.setReorderLevel(invRequest.reorderLevel());
//        newInv.setStockQuantity(invRequest.stockQuantity());
//        return inventoryRepo.save(newInv);
//    }
//
//    public void deleteInventory(Long inventory_id){
//        Inventory inv = inventoryRepo.findById(inventory_id).orElseThrow(()-> new OrderException("Row associated with this ID cannot be found", HttpStatus.NOT_FOUND));
//        inventoryRepo.delete(inv);
//    }

    public InvPageResponse getPages(Integer page_number){
        if(page_number==null){
            page_number = 0;
        }
        Pageable page = PageRequest.of(page_number, 5, Sort.by(Sort.Direction.DESC, "id"));
        Page<Inventory> result = inventoryRepo.findAll(page);
        List<InvResponse> content = result.getContent().stream()
                .map(InvResponse::toResponse)
                .toList();
        return new InvPageResponse(
                content,
                result.getTotalPages()
        );
    }
    // WILL UPDATE BOTH MANGA AND INVENTORY
    // TRANSACTIONAL
    public InvResponse updateMangaAndInventory(InvRequest request, MultipartFile image, Long id) throws IOException {
        Inventory inv = inventoryRepo.findById(id).orElseThrow(()-> new OrderException("Inventory associated with this ID cannot be found", HttpStatus.NOT_FOUND));
        Manga manga = mangaRepository.findById(inv.getManga().getId()).orElseThrow(()-> new OrderException("Manga associated with this ID cannot be found", HttpStatus.NOT_FOUND));
        MangaDescription desc = mangaDescriptionRepository.findById(manga.getMangaDescription().getId()).orElseThrow(()-> new OrderException("Manga Description associated with this ID cannot be found", HttpStatus.NOT_FOUND));
        manga.setTitle(request.title());
        manga.setAuthor(request.author());
        manga.setPublication_year(request.publication_year());
        manga.setCategory(request.category());
        desc.setSynopsis(request.description());
        manga.setAvailability(request.availability());
        if(image != null){
            manga.setImg_url(storageService.upload(image));
        }
        manga.setPrice(request.price());
        inv.setReorderLevel(request.reOrderLevel());
        inv.setStockQuantity(request.stockQuantity());
        inventoryRepo.save(inv);
        mangaRepository.save(manga);
        mangaDescriptionRepository.save(desc);
        return InvResponse.toResponse(inventoryRepo.save(inv));
    }
}
