package com.example.demo.inventory.services;

import com.example.demo.exceptions.OrderException;
import com.example.demo.inventory.dto.InvRequest;
import com.example.demo.inventory.entity.Inventory;
import com.example.demo.inventory.repository.InventoryRepo;
import com.example.demo.product.entity.Product;
import com.example.demo.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryServices {

    @Autowired
    InventoryRepo inventoryRepo;

    @Autowired
    ProductRepository productRepository;

    public Inventory addInventory(InvRequest invRequest){
        if(invRequest.product_id() == null || invRequest.reorderLevel() == null || invRequest.stockQuantity() == null){
            throw new OrderException("Missing required field", HttpStatus.BAD_REQUEST);
        }
        Product product = productRepository.findById(invRequest.product_id()).orElseThrow(()-> new OrderException("Product associated with this ID cannot be found", HttpStatus.NOT_FOUND));
        Inventory newInv = new Inventory();
        newInv.setProduct(product);
        newInv.setStockQuantity(invRequest.stockQuantity());
        newInv.setReorderLevel(invRequest.reorderLevel());
        return inventoryRepo.save(newInv);
    }

    public List<Inventory> getInventoryList(){
        return inventoryRepo.findAll();
    }

    public Inventory getInventory(Long inventory_id){
        return inventoryRepo.findById(inventory_id).orElseThrow(()-> new OrderException("Row associated with this ID cannot be found", HttpStatus.NOT_FOUND));
    }

    public Inventory updateInventory(Long inventory_id, InvRequest invRequest){
        Inventory newInv = inventoryRepo.findById(inventory_id).orElseThrow(()-> new OrderException("Row with this associated ID cannot be found", HttpStatus.NOT_FOUND));
        Product product = productRepository.findById(invRequest.product_id()).orElseThrow(()-> new OrderException("Product with this associated ID cannot be found", HttpStatus.NOT_FOUND));
        newInv.setProduct(product);
        newInv.setReorderLevel(invRequest.reorderLevel());
        newInv.setStockQuantity(invRequest.stockQuantity());
        return inventoryRepo.save(newInv);
    }

    public void deleteInventory(Long inventory_id){
        Inventory inv = inventoryRepo.findById(inventory_id).orElseThrow(()-> new OrderException("Row associated with this ID cannot be found", HttpStatus.NOT_FOUND));
        inventoryRepo.delete(inv);
    }
}
