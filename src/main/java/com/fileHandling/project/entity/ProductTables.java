package com.fileHandling.project.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductTables {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // Service mein 'name' use kiya hai tumne

    @Column(length = 1000)
    private String description;

    private double price;
    private String category;
    private Integer stock;
    private String brand;
    private Double discountPercentage;
    private Float rating;
    private String sku; 
    private String availabilityStatus;
    private String warrantyInformation;
    private String shippingInformation;
    private String returnPolicy;
    private List<String> tag;
    private Integer weight;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String thumbnail;
    
    @Embedded
    private Dimensions dimensions;

    // --- CLOUDINARY FIELDS ---
    @ElementCollection
    private List<String> imageUrl;   // Service: pr.setImageUrl(imageUrl)
    private String publicId;   // Service: pr.setPublicId(setPublicId)

//    // Product ki extra images gallery ke liye
//    @ElementCollection
//    @CollectionTable(name = "product_gallery", joinColumns = @JoinColumn(name = "product_id"))
//    private List<String> galleryImages;
   

    // Reviews relationship
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private List<Review> reviews;

    private String status; // e.g., Low Stock, In Stock
    
}