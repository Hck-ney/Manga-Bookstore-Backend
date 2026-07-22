package com.example.demo.inventory.repository;

import com.example.demo.inventory.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepo extends JpaRepository<Inventory, Long> {
    Inventory findByMangaId(Long id);
}
