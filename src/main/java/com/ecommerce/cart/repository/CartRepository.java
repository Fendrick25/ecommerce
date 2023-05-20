package com.ecommerce.cart.repository;

import com.ecommerce.cart.domain.Cart;
import com.ecommerce.cart.domain.CartItem;

import java.util.UUID;

public interface CartRepository {
    Cart getCart(String userEmail);
    Cart saveCartItem(String userEmail, CartItem cartItem);
    Cart deleteCartItem(String userEmail, UUID cartItemId);
    CartItem getCartItem(String userEmail, UUID cartItemId);
}
