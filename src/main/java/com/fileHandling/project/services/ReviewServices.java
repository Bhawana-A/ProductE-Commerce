package com.fileHandling.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fileHandling.project.entity.ProductTables;
import com.fileHandling.project.entity.Review;
import com.fileHandling.project.repo.ProjectRepo;
import com.fileHandling.project.repo.ReviewRepo;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Service
@Slf4j
public class ReviewServices {

    @Autowired
    private ReviewRepo reviewRepo;

    @Autowired
    private ProjectRepo productRepo;

    // Add Review to Product
    public ProductTables addReviewToProduct(Long productId, Review review) {
        log.info("Adding review to product ID: {}", productId);

        ProductTables product = productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));

        review.setDate(LocalDateTime.now());
        product.getReviews().add(review);

        return productRepo.save(product);
    }

    // Update Review
    public Review updateReview(Long reviewId, Review updatedReview) {
        Review existing = reviewRepo.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Review not found with id: " + reviewId));

        existing.setRating(updatedReview.getRating());
        existing.setComment(updatedReview.getComment());
        existing.setReviewerName(updatedReview.getReviewerName());
        existing.setReviewerEmail(updatedReview.getReviewerEmail());
        existing.setDate(LocalDateTime.now());

        return reviewRepo.save(existing);
    }

    // Delete Review
    public void deleteReview(Long reviewId) {
        reviewRepo.deleteById(reviewId);
    }
}
