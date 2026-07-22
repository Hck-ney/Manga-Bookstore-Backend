package com.example.demo.mangaDescription.repository;

import com.example.demo.mangaDescription.entity.MangaDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MangaDescriptionRepository extends JpaRepository<MangaDescription, Long> {
}
