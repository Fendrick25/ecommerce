package com.ecommerce.cart.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
@RequiredArgsConstructor
public class CartDto {
    private final UUID id;
    private final List<CartItemDto> items;
    private final BigDecimal total;
}
