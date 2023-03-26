package com.ecommerce.cart.repository;

import com.ecommerce.cart.domain.Cart;
import com.ecommerce.cart.domain.CartItem;

import java.util.UUID;

public interface CartRepository {
    Cart getCart(UUID userId);
    void addCartItem(UUID userId, CartItem cartItem);
    void deleteCartItem(UUID userId, UUID cartItemId);
}
