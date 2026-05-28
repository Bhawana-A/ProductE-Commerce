package com.fileHandling.project.services;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.fileHandling.project.entity.Dimensions;
import com.fileHandling.project.entity.ProductTables;
import com.fileHandling.project.entity.Review;
import com.fileHandling.project.repo.ProjectRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProjectServices {

    @Autowired
    private ProjectRepo repo;

    @Autowired
    private Cloudinary cloudinary;

    // =========================
    // 1. GET ALL PRODUCTS
    // =========================

    public List<ProductTables> getAll() {

        log.info("Fetching all products");

        return repo.findAll();
    }

    // =========================
    // 2. CREATE PRODUCT
    // =========================

    public ProductTables createProduct(

            String name,
            String description,
            double price,
            String category,
            Integer stock,
            String brand,
            Double discountPercentage,
            Float rating,
            String sku,
            String availabilityStatus,
            String warrantyInformation,
            String shippingInformation,
            String returnPolicy,
            double width,
            double height,
            double depth,
            List<String> tag,
            Integer weight,
            String status,
            MultipartFile[] files

    ) {

        ProductTables product = new ProductTables();

        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setCategory(category);
        product.setStock(stock);
        product.setBrand(brand);
        product.setDiscountPercentage(discountPercentage);
        product.setRating(rating);
        product.setSku(sku);
        product.setAvailabilityStatus(availabilityStatus);
        product.setWarrantyInformation(warrantyInformation);
        product.setShippingInformation(shippingInformation);
        product.setReturnPolicy(returnPolicy);
        product.setTag(tag);
        product.setWeight(weight);
        product.setStatus(status);

        // =========================
        // Dimensions
        // =========================

        Dimensions dimensions = new Dimensions();

        dimensions.setWidth(width);
        dimensions.setHeight(height);
        dimensions.setDepth(depth);

        product.setDimensions(dimensions);

        // =========================
        // MULTIPLE IMAGE UPLOAD
        // =========================

        if (files != null && files.length > 0) {

            try {

                List<String> imageUrls =
                        new ArrayList<>();

                for (MultipartFile file : files) {

                    Map uploadResult =
                            cloudinary.uploader().upload(
                                    file.getBytes(),
                                    ObjectUtils.emptyMap()
                            );

                    String uploadedImageUrl =
                            uploadResult.get("secure_url")
                                    .toString();

                    imageUrls.add(uploadedImageUrl);

                    // First image = thumbnail
                    if (product.getThumbnail() == null) {

                        product.setThumbnail(
                                uploadedImageUrl);
                    }

                    product.setPublicId(
                            uploadResult.get("public_id")
                                    .toString());
                }

                product.setImageUrl(imageUrls);

            } catch (IOException e) {

                throw new RuntimeException(
                        "Image upload failed : "
                                + e.getMessage());
            }
        }

        product.setCreatedAt(LocalDateTime.now());

        product.setUpdatedAt(LocalDateTime.now());

        return repo.save(product);
    }

    // =========================
    // 3. UPDATE PRODUCT
    // =========================

    public ProductTables updateProduct(

            Long id,
            ProductTables updatedProduct,
            MultipartFile[] files

    ) {

        Optional<ProductTables> existingOpt =
                repo.findById(id);

        if (existingOpt.isEmpty()) {

            throw new RuntimeException(
                    "Product not found with id : "
                            + id);
        }

        ProductTables existing =
                existingOpt.get();

        // =========================
        // UPDATE FIELDS
        // =========================

        existing.setName(updatedProduct.getName());

        existing.setDescription(
                updatedProduct.getDescription());

        existing.setPrice(
                updatedProduct.getPrice());

        existing.setCategory(
                updatedProduct.getCategory());

        existing.setStock(
                updatedProduct.getStock());

        existing.setBrand(
                updatedProduct.getBrand());

        existing.setDiscountPercentage(
                updatedProduct.getDiscountPercentage());

        existing.setRating(
                updatedProduct.getRating());

        existing.setSku(
                updatedProduct.getSku());

        existing.setAvailabilityStatus(
                updatedProduct.getAvailabilityStatus());

        existing.setWarrantyInformation(
                updatedProduct.getWarrantyInformation());

        existing.setShippingInformation(
                updatedProduct.getShippingInformation());

        existing.setReturnPolicy(
                updatedProduct.getReturnPolicy());

        existing.setTag(
                updatedProduct.getTag());

        existing.setWeight(
                updatedProduct.getWeight());

        existing.setStatus(
                updatedProduct.getStatus());

        existing.setDimensions(
                updatedProduct.getDimensions());

        existing.setReviews(
                updatedProduct.getReviews());

        // =========================
        // MULTIPLE IMAGE UPDATE
        // =========================

        if (files != null && files.length > 0) {

            try {

                List<String> imageUrls =
                        existing.getImageUrl();

                if (imageUrls == null) {

                    imageUrls =
                            new ArrayList<>();
                }

                for (MultipartFile file : files) {

                    Map uploadResult =
                            cloudinary.uploader().upload(
                                    file.getBytes(),
                                    ObjectUtils.emptyMap()
                            );

                    String uploadedImageUrl =
                            uploadResult.get("secure_url")
                                    .toString();

                    imageUrls.add(uploadedImageUrl);

                    if (existing.getThumbnail()
                            == null) {

                        existing.setThumbnail(
                                uploadedImageUrl);
                    }

                    existing.setPublicId(
                            uploadResult.get("public_id")
                                    .toString());
                }

                existing.setImageUrl(imageUrls);

                log.info(
                        "Multiple images uploaded successfully");

            } catch (IOException e) {

                throw new RuntimeException(
                        "Image upload failed during update");
            }
        }

        existing.setUpdatedAt(LocalDateTime.now());

        return repo.save(existing);
    }

    // =========================
    // 4. DELETE PRODUCT
    // =========================

    public void delete(Long id) {

        repo.deleteById(id);
    }

    // =========================
    // 5. GET BY CATEGORY
    // =========================

    public List<ProductTables> getByCategory(
            String category
    ) {

        return repo.findByCategory(category);
    }

    // =========================
    // 6. SEARCH PRODUCT
    // =========================

    public List<ProductTables> search(
            String keyword
    ) {

        return repo
                .findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(
                        keyword,
                        keyword
                );
    }

    // =========================
    // 7. SORTING
    // =========================

    public List<ProductTables> getAllSorted(

            String sortBy,
            String direction

    ) {

        Sort sort =
                direction.equalsIgnoreCase("desc")

                        ? Sort.by(sortBy).descending()

                        : Sort.by(sortBy).ascending();

        return repo.findAll(sort);
    }

    // =========================
    // 8. PAGINATION
    // =========================

    public Page<ProductTables> getAllPaginated(

            int page,
            int size,
            String sortBy

    ) {

        Pageable pageable =
                PageRequest.of(
                        page,
                        size,
                        Sort.by(sortBy)
                );

        return repo.findAll(pageable);
    }

    // =========================
    // 9. ADD REVIEW
    // =========================

    public ProductTables addReviewToProduct(

            Long productId,
            Review review

    ) {

        ProductTables product =
                repo.findById(productId)

                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Product not found with id : "
                                                + productId));

        if (product.getReviews() == null) {

            product.setReviews(
                    new ArrayList<>());
        }

        review.setDate(LocalDateTime.now());

        product.getReviews().add(review);

        return repo.save(product);
    }
}