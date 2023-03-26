package com.ecommerce.cart.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class Cart {
    private UUID id;
    private final UUID userId;
    private List<CartItem> items;
    private BigDecimal total;

    void initiate(){
        id = UUID.randomUUID();
        items = new ArrayList<>();
    }

    void calculateTotal(){
        total = items.stream()
                .map(CartItem::calculateSubTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
