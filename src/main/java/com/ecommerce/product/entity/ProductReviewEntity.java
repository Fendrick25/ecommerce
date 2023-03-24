package com.ecommerce.product.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "product_reviews")
public class ProductReviewEntity {
    @Id
    private UUID id;
    private String description;
    private int rating;

    @ManyToOne
    private ProductEntity product;
}
