package com.ecommerce.cart.repository;

import com.ecommerce.cart.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CartJpaRepository extends JpaRepository<CartEntity, UUID> {
    CartEntity findByUserEmail(String userEmail);
}
