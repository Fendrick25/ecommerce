package com.ecommerce.product.repository;

import com.ecommerce.product.domain.ProductCategory;
import com.ecommerce.product.entity.ProductEntity;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductJpaRepository extends JpaRepository<ProductEntity, UUID> {

    @Cacheable(cacheNames = "findAll", unless="#result == null")
    Page<ProductEntity> findAllByOrderByTotalSoldDesc(Pageable pageable);

    Page<ProductEntity> findByNameContainingIgnoreCase(String name, Pageable pageable);
    Page<ProductEntity> findByCategory(ProductCategory category, Pageable pageable);
    Page<ProductEntity> findByNameContainingIgnoreCaseAndCategory(String name, ProductCategory category, Pageable pageable);
}
