package com.ecommerce.cart.domain;

import com.ecommerce.product.domain.Product;
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
    public void initiateCartItem(CartItem cartItem, Product product) {
        cartItem.initiate(product);
    }

    @Override
    public void calculateCartPrice(Cart cart) {
        cart.calculateTotal();
    }

    @Override
    public void updateCartItem(CartItem cartItem, Product product, int requestedQuantity) {
        cartItem.updateCartItem(product, requestedQuantity);
    }

    @Override
    public void decreaseCartItem(CartItem cartItem, Product product) {
        cartItem.decreaseCartItem(product);
    }
}
