package com.ecommerce.product.dto;

import com.ecommerce.product.domain.ProductCategory;
import com.ecommerce.product.domain.ProductStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
@RequiredArgsConstructor
public class ProductDto {
    private final UUID id;
    private final String name;
    private final BigDecimal price;
    private final String description;
    private final double weight;
    private final int stock;
    private final int totalSold;
    private final double averageRating;
    private final String imageUrl;
    private final ProductStatus status;
    private final ProductCategory category;
    private final List<ProductReviewDto> reviews;
}
