package com.ecommerce.cart.service;

import com.ecommerce.auth.service.JwtService;
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
    private final JwtService jwtService;

    @Override
    public void addCartItem(String token, AddCartItem addCartItem) {
        CartItem cartItem = cartDataMapper.addCartItemToCartItem(addCartItem);
        cartDomainService.initiateCartItem(cartItem);
        cartRepository.addCartItem(jwtService.extractUsername(token.substring(7)), cartItem);
    }

    @Override
    public void deleteCartItem(String token, UUID cartItemId) {
        cartRepository.deleteCartItem(jwtService.extractUsername(token.substring(7)), cartItemId);
    }

    @Override
    public CartDto getCart(String token) {
        Cart cart = cartRepository.getCart(jwtService.extractUsername(token.substring(7)));
        cartDomainService.calculateCartPrice(cart);
        return cartDataMapper.cartToCartDto(cart);
    }
}
