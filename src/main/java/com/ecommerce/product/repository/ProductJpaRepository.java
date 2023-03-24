package com.ecommerce.product.repository;

import com.ecommerce.product.domain.ProductCategory;
import com.ecommerce.product.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductJpaRepository extends JpaRepository<ProductEntity, UUID> {
    Page<ProductEntity> findByNameContaining(String name, Pageable pageable);
    Page<ProductEntity> findByCategoryContaining(ProductCategory category, Pageable pageable);
    Page<ProductEntity> findByNameContainingAndCategory(String name, ProductCategory category, Pageable pageable);
}