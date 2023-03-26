package com.ecommerce.cart.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class CartDomainServiceImpl implements CartDomainService{
    @Override
    public void initiateCart(Cart cart) {
        cart.initiate();
    }

    @Override
    public void initiateCartItem(CartItem cartItem) {
        cartItem.initiate();
    }

    @Override
    public void calculateCartPrice(Cart cart) {
        cart.calculateTotal();
    }
}
