package com.example.demo.inventory.controller;
import com.example.demo.inventory.dto.InvPageResponse;
import com.example.demo.inventory.dto.InvRequest;
import com.example.demo.inventory.dto.InvResponse;
import com.example.demo.inventory.services.InventoryServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class InventoryController {

    @Autowired
    private InventoryServices inventoryServices;

//    @PostMapping("/inventory")
//    public ResponseEntity<Inventory> addInventory(@RequestBody InvRequest invRequest){
//        return new ResponseEntity<>(inventoryServices.addInventory(invRequest), HttpStatus.CREATED);
//    }

//    @GetMapping("/inventory")
//    public List<Inventory> getInventoryList(){
//        return inventoryServices.getInventoryList();
//    }
//
//    @GetMapping("/inventory/{inventory_id}")
//    public Inventory getInventory(@PathVariable Long inventory_id){
//        return inventoryServices.getInventory(inventory_id);
//    }
//
//    @PutMapping("/inventory/{inventory_id}")
//    public Inventory updateInventory(@PathVariable Long inventory_id, @RequestBody  InvRequest invRequest){
//        return inventoryServices.updateInventory(inventory_id, invRequest);
//    }
//
//    @DeleteMapping("/inventory/{inventory_id}")
//    public void deleteInventory(@PathVariable Long inventory_id){
//         inventoryServices.deleteInventory(inventory_id);
//    }

    // ADMIN

    @GetMapping("/admin/inventory")
    public InvPageResponse getPages(@Valid @RequestParam(name = "page", required = false) Integer page_number){
        return inventoryServices.getPages(page_number);
    }

    @PutMapping(value = "/admin/inventory/{id}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<InvResponse> updateMangaAndInventory(@Valid @RequestPart("data") InvRequest invRequest,
                                                       @RequestPart(value = "image", required = false) MultipartFile image,
                                                       @PathVariable Long id) throws IOException {
        return new ResponseEntity<>(inventoryServices.updateMangaAndInventory(invRequest, image, id), HttpStatus.CREATED);
    }
}
