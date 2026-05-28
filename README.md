# Product Management System API

A Spring Boot REST API for managing products, product images, and customer reviews with support for:

* CRUD Operations
* Product Search
* Sorting & Pagination
* Category Filtering
* Multiple Image Uploads
* Cloudinary Image Storage
* Product Reviews

---

# Tech Stack

* Java 17+
* Spring Boot
* Spring Data JPA
* MySQL
* Hibernate
* Lombok
* Cloudinary
* Maven

---

# Project Structure

```bash
src/main/java/com/fileHandling/project
│
├── controller
│   ├── ProductController.java
│   └── ReviewController.java
│
├── entity
│   ├── ProductTables.java
│   ├── Review.java
│   └── Dimensions.java
│
├── repository
│
├── services
│
└── config
```

---

# Features

## Product Features

* Add Product
* Update Product
* Delete Product
* Get All Products
* Search Products
* Filter By Category
* Sorting
* Pagination
* Upload Multiple Images

## Review Features

* Add Review
* Update Review
* Delete Review

---

# Entity Relationships

## Product → Reviews

One product can have multiple reviews.

---

# Product Entity Fields

| Field               | Type         |
| ------------------- | ------------ |
| id                  | Long         |
| name                | String       |
| description         | String       |
| price               | double       |
| category            | String       |
| stock               | Integer      |
| brand               | String       |
| discountPercentage  | Double       |
| rating              | Float        |
| sku                 | String       |
| availabilityStatus  | String       |
| warrantyInformation | String       |
| shippingInformation | String       |
| returnPolicy        | String       |
| tag                 | List<String> |
| weight              | Integer      |
| thumbnail           | String       |
| imageUrl            | List<String> |
| publicId            | String       |
| status              | String       |

---

# API Endpoints

# Product APIs

Base URL:

```bash
/api/products
```

---

## 1. Get All Products

```http
GET /products
```

---

## 2. Create Product

```http
POST /products
```

### Form Data

| Key                 | Type            |
| ------------------- | --------------- |
| name                | String          |
| description         | String          |
| price               | double          |
| category            | String          |
| stock               | Integer         |
| brand               | String          |
| discountPercentage  | Double          |
| rating              | Float           |
| sku                 | String          |
| availabilityStatus  | String          |
| warrantyInformation | String          |
| shippingInformation | String          |
| returnPolicy        | String          |
| tag                 | List<String>    |
| weight              | Integer         |
| status              | String          |
| width               | double          |
| height              | double          |
| depth               | double          |
| files               | MultipartFile[] |

---

## 3. Update Product

```http
PUT /products/{id}
```

---

## 4. Delete Product

```http
DELETE /products/{id}
```

---

## 5. Get Products By Category

```http
GET /products/category/{category}
```

---

## 6. Search Products

```http
GET /products/search?keyword=phone
```

---

## 7. Sorting

```http
GET /products/sorted?sortBy=price&direction=asc
```

---

## 8. Pagination

```http
GET /products/paginated?page=0&size=10&sortBy=id
```

---

# Review APIs

Base URL:

```bash
/api/reviews
```

---

## 1. Add Review

```http
POST /reviews/product/{productId}
```

### Request Body

```json
{
  "rating": 4.5,
  "comment": "Excellent product",
  "reviewerName": "Rahul",
  "reviewerEmail": "rahul@gmail.com"
}
```

---

## 2. Update Review

```http
PUT /reviews/{reviewId}
```

---

## 3. Delete Review

```http
DELETE /reviews/{reviewId}
```

---

# Database Configuration

Configure your `application.properties` file:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/product_db
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

---

# Cloudinary Configuration

```properties
cloudinary.cloud-name=YOUR_CLOUD_NAME
cloudinary.api-key=YOUR_API_KEY
cloudinary.api-secret=YOUR_API_SECRET
```

---

# Future Improvements

* JWT Authentication
* Role Based Authorization
* Swagger Documentation
* Redis Caching
* Docker Deployment
* Unit Testing
* Wishlist & Cart Module
* Payment Gateway Integration

---

# Author

**Bhawana Ahirwar**

---
