package com.ecommerce.product.mapper;

import com.ecommerce.product.domain.Product;
import com.ecommerce.product.domain.ProductReview;
import com.ecommerce.product.dto.CreateProduct;
import com.ecommerce.product.dto.FindProductResponse;
import com.ecommerce.product.dto.ProductDto;
import com.ecommerce.product.dto.ProductReviewDto;
import com.ecommerce.product.entity.ProductEntity;
import com.ecommerce.product.entity.ProductReviewEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductDataMapper {
    public Product createProductToProduct(CreateProduct createProduct){
        return Product.builder()
                .name(createProduct.getName())
                .description(createProduct.getDescription())
                .price(createProduct.getPrice())
                .weight(createProduct.getWeight())
                .stock(createProduct.getStock())
                .build();
    }

    public ProductDto productToProductDto(Product product){
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .stock(product.getStock())
                .weight(product.getWeight())
                .status(product.getStatus())
                .category(product.getCategory())
                .totalSold(product.getTotalSold())
                .averageRating(product.getAverageRating())
                .imageUrl(product.getImageUrl())
                .reviews(product.getReviews().stream()
                        .map(this::productReviewToProductReviewDto)
                        .collect(Collectors.toList()))
                .build();
    }

    public ProductReviewDto productReviewToProductReviewDto(ProductReview productReview){
        return ProductReviewDto.builder()
                .id(productReview.getId())
                .description(productReview.getDescription())
                .rating(productReview.getRating())
                .build();
    }

    public FindProductResponse productsToFindProductResponse(List<Product> products, int page, int size){
        return FindProductResponse.builder()
                .products(products.stream()
                        .map(product -> FindProductResponse.Product.builder()
                                .id(product.getId())
                                .name(product.getName())
                                .price(product.getPrice())
                                .totalSold(product.getTotalSold())
                                .averageRating(product.getAverageRating())
                                .imageUrl(product.getImageUrl())
                        .build())
                        .collect(Collectors.toList()))
                .page(page)
                .size(size)
                .build();
    }

    public ProductEntity productToProductEntity(Product product){
        return ProductEntity.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stock(product.getStock())
                .weight(product.getWeight())
                .totalSold(product.getTotalSold())
                .imageUrl(product.getImageUrl())
                .status(product.getStatus())
                .category(product.getCategory())
                .reviews(product.getReviews().stream()
                        .map(this::productReviewToProductReviewEntity)
                        .collect(Collectors.toList()))
                .build();
    }

    public Product productEntityToProduct(ProductEntity productEntity){
        return Product.builder()
                .id(productEntity.getId())
                .name(productEntity.getName())
                .description(productEntity.getDescription())
                .price(productEntity.getPrice())
                .stock(productEntity.getStock())
                .weight(productEntity.getWeight())
                .totalSold(productEntity.getTotalSold())
                .imageUrl(productEntity.getImageUrl())
                .status(productEntity.getStatus())
                .category(productEntity.getCategory())
                .reviews(productEntity.getReviews().stream()
                        .map(this::productReviewEntityToProductReview)
                        .collect(Collectors.toList()))
                .build();
    }

    private ProductReviewEntity productReviewToProductReviewEntity(ProductReview productReview){
        return ProductReviewEntity.builder()
                .id(productReview.getId())
                .product(ProductEntity.builder()
                        .id(productReview.getProductId())
                        .build())
                .rating(productReview.getRating())
                .description(productReview.getDescription())
                .build();
    }

    private ProductReview productReviewEntityToProductReview(ProductReviewEntity productReviewEntity){
        return ProductReview.builder()
                .id(productReviewEntity.getId())
                .productId(productReviewEntity.getProduct().getId())
                .rating(productReviewEntity.getRating())
                .description(productReviewEntity.getDescription())
                .build();
    }
}
