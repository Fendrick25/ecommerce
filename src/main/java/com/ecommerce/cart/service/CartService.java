package com.ecommerce.cart.service;


import com.ecommerce.cart.dto.AddCartItem;
import com.ecommerce.cart.dto.CartDto;

import java.util.UUID;

public interface CartService {
    void addCartItem(String token, AddCartItem addCartItem);
    void deleteCartItem(String token, UUID cartItemId);
    CartDto getCart(String token);
}
