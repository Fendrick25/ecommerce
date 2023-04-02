package com.ecommerce.cart.repository;

import com.ecommerce.cart.domain.Cart;
import com.ecommerce.cart.domain.CartItem;

import java.util.UUID;

public interface CartRepository {
    Cart getCart(String userEmail);
    void addCartItem(String userEmail, CartItem cartItem);
    void deleteCartItem(String userEmail, UUID cartItemId);
}
