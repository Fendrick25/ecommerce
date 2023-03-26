package com.ecommerce.cart.domain;


public interface CartDomainService {
    void initiateCart(Cart cart);
    void initiateCartItem(CartItem cartItem);
    void calculateCartPrice(Cart cart);
}
