package com.ecommerce.product.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Getter
@Builder
@RequiredArgsConstructor
public class ProductReviewDto {
    private final UUID id;
    private final String description;
    private final int rating;
}
