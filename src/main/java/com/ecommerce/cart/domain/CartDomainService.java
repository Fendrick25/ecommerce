package com.ecommerce.cart.domain;


import com.ecommerce.product.domain.Product;

public interface CartDomainService {
    void initiateCart(Cart cart);
    void initiateCartItem(CartItem cartItem, Product product);
    void calculateCartPrice(Cart cart);
    void updateCartItem(CartItem cartItem, Product product, int requestedQuantity);
    void decreaseCartItem(CartItem cartItem, Product product);
}
