package com.ecommerce.product.repository;

import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.product.domain.Product;
import com.ecommerce.product.domain.ProductCategory;
import com.ecommerce.product.entity.ProductEntity;
import com.ecommerce.product.mapper.ProductDataMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository{

    private final ProductJpaRepository productJpaRepository;
    private final ProductDataMapper productDataMapper;

    @Override
    public Product saveProduct(Product product) {
        return productDataMapper.productEntityToProduct(productJpaRepository.save(productDataMapper.productToProductEntity(product)));
    }

    @Override
    public Product getProduct(UUID productId) {
        return productDataMapper.productEntityToProduct(productJpaRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product with id: " + productId + " not found")));
    }

    @Override
    public List<Product> findAllProductSortByTotalSold(int page, int size) {
        Page<ProductEntity> products = productJpaRepository.findAllByOrderByTotalSoldDesc(PageRequest.of(page - 1, size));
        return products.getContent().stream()
                .map(productDataMapper::productEntityToProduct)
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> findProductByName(String name, int page, int size) {
        Page<ProductEntity> products = productJpaRepository.findByNameContainingIgnoreCase(name, PageRequest.of(page - 1, size));
        return products.getContent().stream()
                .map(productDataMapper::productEntityToProduct)
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> findProductByCategory(ProductCategory category, int page, int size) {
        Page<ProductEntity> products = productJpaRepository.findByCategory(category, PageRequest.of(page - 1, size));
        return products.getContent().stream()
                .map(productDataMapper::productEntityToProduct)
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> findProductByNameAndCategory(String name, ProductCategory category, int page, int size) {
        Page<ProductEntity> products = productJpaRepository.findByNameContainingIgnoreCaseAndCategory(name, category, PageRequest.of(page - 1, size));
        return products.getContent().stream()
                .map(productDataMapper::productEntityToProduct)
                .collect(Collectors.toList());
    }
}
