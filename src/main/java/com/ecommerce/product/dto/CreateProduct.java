package com.ecommerce.product.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Getter
@Builder
@RequiredArgsConstructor
public class CreateProduct {

    @NotNull
    @Size(min = 4, max = 99, message = "product name must be between 4 and 99 characters")
    private final String name;

    @NotNull
    @Size(min = 4, max = 5000, message = "product name must be between 4 and 5000 characters")
    private final String description;

    @NotNull
    @Positive(message = "product price must be greater than 0")
    private final BigDecimal price;

    @NotNull
    @Positive(message = "product weight must be greater than 0")
    private final double weight;

    @NotNull
    @Min(value = 0, message = "product stock cannot be negative")
    private final int stock;
}
