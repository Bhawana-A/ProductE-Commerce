package com.fileHandling.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.fileHandling.project.entity.ProductTables;
import com.fileHandling.project.services.ProjectServices;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProjectServices service;

    // =========================
    // 1. GET ALL PRODUCTS
    // =========================

    @GetMapping
    public List<ProductTables> getAll() {

        return service.getAll();
    }

    // =========================
    // 2. CREATE PRODUCT
    // =========================

    @PostMapping
    public ProductTables createProduct(

            @RequestParam("name") String name,

            @RequestParam("description")
            String description,

            @RequestParam("price")
            double price,

            @RequestParam("category")
            String category,

            @RequestParam("stock")
            Integer stock,

            @RequestParam("brand")
            String brand,

            @RequestParam("discountPercentage")
            Double discountPercentage,

            @RequestParam("rating")
            Float rating,

            @RequestParam("sku")
            String sku,

            @RequestParam("availabilityStatus")
            String availabilityStatus,

            @RequestParam("warrantyInformation")
            String warrantyInformation,

            @RequestParam("shippingInformation")
            String shippingInformation,

            @RequestParam("returnPolicy")
            String returnPolicy,

            @RequestParam("tag")
            List<String> tag,

            @RequestParam("weight")
            Integer weight,

            @RequestParam("status")
            String status,

            @RequestParam("width")
            double width,

            @RequestParam("height")
            double height,

            @RequestParam("depth")
            double depth,

            // MULTIPLE FILES
            @RequestParam("files")
            MultipartFile[] files

    ) {

        return service.createProduct(

                name,
                description,
                price,
                category,
                stock,
                brand,
                discountPercentage,
                rating,
                sku,
                availabilityStatus,
                warrantyInformation,
                shippingInformation,
                returnPolicy,
                width,
                height,
                depth,
                tag,
                weight,
                status,
                files
        );
    }

    // =========================
    // 3. UPDATE PRODUCT
    // =========================

    @PutMapping("/{id}")
    public ProductTables updateProduct(

            @PathVariable Long id,

            @ModelAttribute ProductTables product,

            @RequestParam(required = false)
            MultipartFile[] files

    ) {

        return service.updateProduct(
                id,
                product,
                files
        );
    }

    // =========================
    // 4. DELETE PRODUCT
    // =========================

    @DeleteMapping("/{id}")
    public String deleteProduct(
            @PathVariable Long id
    ) {

        service.delete(id);

        return "Product deleted successfully with id : "
                + id;
    }

    // =========================
    // 5. GET BY CATEGORY
    // =========================

    @GetMapping("/category/{category}")
    public List<ProductTables> getByCategory(
            @PathVariable String category
    ) {

        return service.getByCategory(category);
    }

    // =========================
    // 6. SEARCH PRODUCT
    // =========================

    @GetMapping("/search")
    public List<ProductTables> searchProducts(

            @RequestParam String keyword

    ) {

        return service.search(keyword);
    }

    // =========================
    // 7. SORTING
    // =========================

    @GetMapping("/sorted")
    public List<ProductTables> getAllSorted(

            @RequestParam String sortBy,

            @RequestParam(defaultValue = "asc")
            String direction

    ) {

        return service.getAllSorted(
                sortBy,
                direction
        );
    }

    // =========================
    // 8. PAGINATION
    // =========================

    @GetMapping("/paginated")
    public Page<ProductTables> getAllPaginated(

            @RequestParam(defaultValue = "0")
            int page,

            @RequestParam(defaultValue = "10")
            int size,

            @RequestParam(defaultValue = "id")
            String sortBy

    ) {

        return service.getAllPaginated(
                page,
                size,
                sortBy
        );
    }

    // =========================
    // 9. ADD REVIEW
    // =========================

//    @PostMapping("/{id}/reviews")
//    public ProductTables addReview(
//
//            @PathVariable Long id,
//
//            @RequestBody Review review
//
//    ) {
//
//        return service.addReviewToProduct(
//                id,
//                review
//        );
//   }
}