package com.ecommerce.cart.domain;

import com.ecommerce.exception.InvalidRequestException;
import com.ecommerce.product.domain.Product;
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
    private final UUID productId;
    private final String productName;
    private final BigDecimal price;
    private int quantity;
    private BigDecimal subTotal;
    private final String imageUrl;

    void initiate(Product product){
        id = UUID.randomUUID();
        validateStock(product);
    }

    public BigDecimal calculateSubTotal(){
        subTotal = price.multiply(BigDecimal.valueOf(quantity));
        return subTotal;
    }

    public void updateCartItem(Product product, int requestedQuantity){
        quantity = requestedQuantity;
        validateStock(product);
    }

    public void decreaseCartItem(Product product){
        quantity -= 1;
        validateStock(product);
    }

    public void validateStock(Product product){
        if(product.getStock() < quantity)
            throw new InvalidRequestException("Not enough product stock, current stock: " + product.getStock() + ", requested stock: " + quantity);
    }

}
