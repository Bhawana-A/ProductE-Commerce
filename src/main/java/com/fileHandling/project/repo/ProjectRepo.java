package com.fileHandling.project.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fileHandling.project.entity.ProductTables;

import java.util.List;

@Repository
public interface ProjectRepo extends JpaRepository<ProductTables, Long> {

    // Category ke base par products fetch karne ke liye
    List<ProductTables> findByCategory(String category);

    // Searching by name or description (case-insensitive)
    List<ProductTables> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String name, String description);
}
