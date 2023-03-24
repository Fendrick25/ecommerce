package com.ecommerce.product.repository;

import com.ecommerce.product.domain.Product;
import com.ecommerce.product.domain.ProductCategory;

import java.util.List;
import java.util.UUID;

public interface ProductRepository {
    Product saveProduct(Product product);
    Product getProduct(UUID productId);
    List<Product> findAllProductSortByTotalSold(int page, int size);
    List<Product> findProductByName(String name, int page, int size);
    List<Product> findProductByCategory(ProductCategory category, int page, int size);
    List<Product> findProductByNameAndCategory(String name, ProductCategory category, int page, int size);
}
