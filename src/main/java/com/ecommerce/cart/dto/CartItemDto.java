package com.ecommerce.cart.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Builder
@RequiredArgsConstructor
public class CartItemDto {
    private final UUID id;
    private final UUID productId;
    private final String productName;
    private final BigDecimal price;
    private final int quantity;
    private final BigDecimal subTotal;
    private final String imageUrl;
}
