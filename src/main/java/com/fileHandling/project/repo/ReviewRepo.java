package com.fileHandling.project.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fileHandling.project.entity.Review;


@Repository
public interface ReviewRepo extends JpaRepository<Review, Long>{

}
