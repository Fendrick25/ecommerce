package com.ecommerce.product.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Clock;

@Component
@RequiredArgsConstructor
public class ProductDomainServiceImpl implements ProductDomainService{

    private final Clock clock;

    @Override
    public void initiate(Product product) {
       product.initializeProduct();
       product.setCreatedAt(clock.instant().getEpochSecond());
    }

    @Override
    public void updateStock(Product product, int stock) {
        product.updateStock(stock);
    }

    @Override
    public void calculateRating(Product product) {
        product.calculateRating();
    }
}
