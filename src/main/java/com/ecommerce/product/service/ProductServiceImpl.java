package com.ecommerce.product.service;

import com.ecommerce.product.domain.Product;
import com.ecommerce.product.domain.ProductCategory;
import com.ecommerce.product.domain.ProductDomainService;
import com.ecommerce.product.dto.CreateProduct;
import com.ecommerce.product.dto.FindProductResponse;
import com.ecommerce.product.dto.ProductDto;
import com.ecommerce.product.mapper.ProductDataMapper;
import com.ecommerce.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.UUID;

@Component
@Validated
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductDomainService productDomainService;
    private final ProductRepository productRepository;
    private final ProductDataMapper productDataMapper;

    @Override
    public ProductDto createProduct(CreateProduct createProduct) {
        Product product = productDataMapper.createProductToProduct(createProduct);
        productDomainService.initiate(product);
        return productDataMapper.productToProductDto(productRepository.saveProduct(product));
    }

    @Override
    public ProductDto getProduct(UUID productId) {
        Product product = productRepository.getProduct(productId);
        productDomainService.calculateRating(product);
        return productDataMapper.productToProductDto(product);
    }

    @Override
    public FindProductResponse findProductByName(String name, int page, int size) {
        List<Product> products = productRepository.findProductByName(name, page, size);
        return productDataMapper.productsToFindProductResponse(products, page, size);
    }

    @Override
    public FindProductResponse findProductByCategory(ProductCategory category, int page, int size) {
        List<Product> products = productRepository.findProductByCategory(category, page, size);
        return productDataMapper.productsToFindProductResponse(products, page, size);
    }

    @Override
    public FindProductResponse findProductByNameAndCategory(String name, ProductCategory category, int page, int size) {
        List<Product> products = productRepository.findProductByNameAndCategory(name, category, page, size);
        return productDataMapper.productsToFindProductResponse(products, page, size);
    }
}
