package com.example.demo.inventory.controller;
import com.example.demo.inventory.dto.InvRequest;
import com.example.demo.inventory.entity.Inventory;
import com.example.demo.inventory.services.InventoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InventoryController {

    @Autowired
    private InventoryServices inventoryServices;

    @PostMapping("/inventory")
    public Inventory addInventory(@RequestBody InvRequest invRequest){
        return inventoryServices.addInventory(invRequest);
    }

    @GetMapping("/inventory")
    public List<Inventory> getInventoryList(){
        return inventoryServices.getInventoryList();
    }

    @GetMapping("/inventory/{inventory_id}")
    public Inventory getInventory(@PathVariable Long inventory_id){
        return inventoryServices.getInventory(inventory_id);
    }

    @PutMapping("/inventory/{inventory_id}")
    public Inventory updateInventory(@PathVariable Long inventory_id, @RequestBody  InvRequest invRequest){
        return inventoryServices.updateInventory(inventory_id, invRequest);
    }

    @DeleteMapping("/inventory/{inventory_id}")
    public void deleteInventory(@PathVariable Long inventory_id){
         inventoryServices.deleteInventory(inventory_id);
    }

}
