package com.ecommerce.product.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@Builder
@RequiredArgsConstructor
public class GetProductResponse {
    private final ProductDto product;
}
