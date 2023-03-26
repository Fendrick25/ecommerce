package com.ecommerce.cart.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class CartItem {
    private UUID id;
    private final UUID cartId;
    private final UUID productId;
    private final BigDecimal price;
    private final int quantity;
    private BigDecimal subTotal;

    void initiate(){
        id = UUID.randomUUID();
    }

    public BigDecimal calculateSubTotal(){
        subTotal = price.multiply(BigDecimal.valueOf(quantity));
        return subTotal;
    }
}
