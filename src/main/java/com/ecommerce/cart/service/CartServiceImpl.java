package com.ecommerce.cart.service;

import com.ecommerce.cart.domain.Cart;
import com.ecommerce.cart.domain.CartDomainService;
import com.ecommerce.cart.domain.CartItem;
import com.ecommerce.cart.dto.AddCartItem;
import com.ecommerce.cart.dto.CartDto;
import com.ecommerce.cart.mapper.CartDataMapper;
import com.ecommerce.cart.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CartServiceImpl implements CartService{

    private final CartDomainService cartDomainService;
    private final CartRepository cartRepository;
    private final CartDataMapper cartDataMapper;

    @Override
    public void addCartItem(UUID userId, AddCartItem addCartItem) {
        CartItem cartItem = cartDataMapper.addCartItemToCartItem(addCartItem);
        cartDomainService.initiateCartItem(cartItem);
        cartRepository.addCartItem(userId, cartItem);
    }

    @Override
    public void deleteCartItem(UUID userId, UUID cartItemId) {
        cartRepository.deleteCartItem(userId, cartItemId);
    }

    @Override
    public CartDto getCart(UUID userId) {
        Cart cart = cartRepository.getCart(userId);
        cartDomainService.calculateCartPrice(cart);
        return cartDataMapper.cartToCartDto(cart);
    }
}
