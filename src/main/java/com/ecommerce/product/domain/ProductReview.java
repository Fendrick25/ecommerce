package com.ecommerce.product.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class ProductReview {

    private final UUID id;
    private final UUID productId;
    private final String description;
    private final int rating;
}
