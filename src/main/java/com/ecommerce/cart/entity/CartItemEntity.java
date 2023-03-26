package com.ecommerce.cart.entity;

import com.ecommerce.product.entity.ProductEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "cart_items")
public class CartItemEntity {
    @Id
    private UUID id;
    private int quantity;
    @ManyToOne(fetch = FetchType.LAZY)
    private ProductEntity product;
    @ManyToOne(fetch = FetchType.LAZY)
    private CartEntity cart;
}
