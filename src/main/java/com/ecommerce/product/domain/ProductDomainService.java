package com.ecommerce.product.domain;

public interface ProductDomainService {

    void initiate(Product product);
    void updateStock(Product product, int stock);
    void calculateRating(Product product);
}
