package com.ecommerce.product.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
@RequiredArgsConstructor
public class FindProductResponse {

    private final List<Product> products;
    private final int page;
    private final int size;

    @Getter
    @Builder
    @RequiredArgsConstructor
    public static class Product{
        private final UUID id;
        private final String name;
        private final BigDecimal price;
        private final int totalSold;
        private final double averageRating;
        private final String imageUrl;
    }
}


