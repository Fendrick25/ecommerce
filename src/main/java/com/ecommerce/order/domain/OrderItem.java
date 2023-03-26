package com.ecommerce.order.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class OrderItem {
    private UUID id;
    private final UUID productId;
    private final int quantity;
    private final BigDecimal price;
    private BigDecimal subTotal;

    void initiateOrderItem(){
        id = UUID.randomUUID();
    }

    public BigDecimal calculateSubTotal(){
        initiateOrderItem();
        subTotal = price.multiply(BigDecimal.valueOf(quantity));
        return subTotal;
    }
}
