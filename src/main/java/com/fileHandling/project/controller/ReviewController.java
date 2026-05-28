package com.fileHandling.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fileHandling.project.entity.ProductTables;
import com.fileHandling.project.entity.Review;
import com.fileHandling.project.services.ReviewServices;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewServices reviewServices;

    // Add Review to Product
    @PostMapping("/product/{productId}")
    public ProductTables addReview(@PathVariable Long productId, @RequestBody Review review) {
        return reviewServices.addReviewToProduct(productId, review);
    }

    // Update Review
    @PutMapping("/{reviewId}")
    public Review updateReview(@PathVariable Long reviewId, @RequestBody Review review) {
        return reviewServices.updateReview(reviewId, review);
    }

    // Delete Review
    @DeleteMapping("/{reviewId}")
    public String deleteReview(@PathVariable Long reviewId) {
        reviewServices.deleteReview(reviewId);
        return "Review deleted successfully with id: " + reviewId;
    }
}
