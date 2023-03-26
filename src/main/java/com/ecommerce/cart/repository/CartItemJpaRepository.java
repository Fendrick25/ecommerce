package com.ecommerce.cart.repository;

import com.ecommerce.cart.entity.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CartItemJpaRepository extends JpaRepository<CartItemEntity, UUID> {
}
