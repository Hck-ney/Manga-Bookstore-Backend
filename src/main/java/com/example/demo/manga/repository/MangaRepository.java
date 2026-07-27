package com.example.demo.manga.repository;
import com.example.demo.enums.Availability;
import com.example.demo.manga.entity.Manga;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MangaRepository extends JpaRepository<Manga, Long>, JpaSpecificationExecutor<Manga> {
    Page<Manga> findByAvailability(Availability available, Pageable pageable);
}

