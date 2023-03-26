package com.ecommerce.cart.repository;

import com.ecommerce.cart.domain.Cart;
import com.ecommerce.cart.domain.CartItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CartRepositoryImpl implements CartRepository{
    private final CartItemJpaRepository cartItemJpaRepository;
    private final CartJpaRepository cartJpaRepository;

    @Override
    public Cart getCart(UUID userId) {
        return null;
    }

    @Override
    public void addCartItem(UUID userId, CartItem cartItem) {

    }

    @Override
    public void deleteCartItem(UUID userId, UUID cartItemId) {

    }
}
