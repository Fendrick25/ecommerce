package com.ecommerce.cart.service;


import com.ecommerce.cart.dto.CartItemDto;
import com.ecommerce.cart.dto.AddCartItem;
import com.ecommerce.cart.dto.CartDto;
import com.ecommerce.cart.dto.UpdateCartItem;

import java.util.UUID;

public interface CartService {
    CartDto addCartItem(String token, AddCartItem cartItemRequest);
    CartDto deleteCartItem(String token, UUID cartItemId);
    CartDto getCart(String token);
    CartDto updateCartItem(String token, UpdateCartItem updateCartItem);
    CartDto decreaseCartItem(String token, UUID cartItemID);
}
