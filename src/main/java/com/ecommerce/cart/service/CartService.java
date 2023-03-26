package com.ecommerce.cart.service;


import com.ecommerce.cart.dto.AddCartItem;
import com.ecommerce.cart.dto.CartDto;

import java.util.UUID;

public interface CartService {
    void addCartItem(UUID userId, AddCartItem addCartItem);
    void deleteCartItem(UUID userId, UUID cartItemId);
    CartDto getCart(UUID userId);
}
