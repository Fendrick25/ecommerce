package com.ecommerce.product.service;

import com.ecommerce.product.domain.ProductCategory;
import com.ecommerce.product.dto.CreateProduct;
import com.ecommerce.product.dto.FindProductResponse;
import com.ecommerce.product.dto.ProductDto;

import java.util.UUID;

public interface ProductService {
    ProductDto createProduct(CreateProduct createProduct);
    ProductDto getProduct(UUID productId);
    FindProductResponse findAllProductSortByTotalSold(int page, int size);
    FindProductResponse findProductByName(String name, int page, int size);
    FindProductResponse findProductByCategory(ProductCategory category, int page, int size);
    FindProductResponse findProductByNameAndCategory(String name, ProductCategory category, int page, int size);

}
