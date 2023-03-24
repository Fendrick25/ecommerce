package com.ecommerce.product.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Getter
@Builder
@RequiredArgsConstructor
public class CreateProduct {
    private final String name;
    private final String description;
    private final BigDecimal price;
    private final double weight;
    private final int stock;
}
