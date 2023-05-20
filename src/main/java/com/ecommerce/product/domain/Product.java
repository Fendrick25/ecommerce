package com.ecommerce.product.domain;

import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class Product {
    private UUID id;
    private final String name;
    private final String description;
    private final BigDecimal price;
    private final double weight;
    private final int stock;
    private int totalSold;
    private double averageRating;
    private String imageUrl;
    private ProductStatus status;
    private ProductCategory category;
    private List<ProductReview> reviews;

    @Setter
    private long createdAt;

    public void initializeProduct(){
        id = UUID.randomUUID();
        status = ProductStatus.ACTIVE;
        category = ProductCategory.ALL;
        totalSold = 0;
        averageRating = 0;
        reviews = new ArrayList<>();
        imageUrl = " ";
        updateStock(stock);
    }

    public void updateStock(int stock){
        if(stock <= 0)
            status = ProductStatus.NOT_ACTIVE;
    }

    public void calculateRating(){
        averageRating = reviews.stream()
                .mapToInt(ProductReview::getRating)
                .average()
                .orElse(0.0);
    }
}

