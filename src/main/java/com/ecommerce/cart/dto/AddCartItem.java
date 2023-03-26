package com.ecommerce.cart.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Getter
@Builder
@RequiredArgsConstructor
public class AddCartItem {
    private final UUID productId;
    private final int quantity;
}
