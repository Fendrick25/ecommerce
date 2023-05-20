package com.ecommerce.cart.service;

import com.ecommerce.auth.service.JwtService;
import com.ecommerce.cart.domain.Cart;
import com.ecommerce.cart.domain.CartDomainService;
import com.ecommerce.cart.domain.CartItem;
import com.ecommerce.cart.dto.AddCartItem;
import com.ecommerce.cart.dto.CartDto;
import com.ecommerce.cart.dto.UpdateCartItem;
import com.ecommerce.cart.mapper.CartDataMapper;
import com.ecommerce.cart.repository.CartRepository;
import com.ecommerce.product.domain.Product;
import com.ecommerce.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CartServiceImpl implements CartService{

    private final CartDomainService cartDomainService;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CartDataMapper cartDataMapper;
    private final JwtService jwtService;

    @Override
    public CartDto addCartItem(String token, AddCartItem addCartItem) {
        Product product = productRepository.getProduct(addCartItem.getProductId());
        CartItem cartItem = cartDataMapper.addCartItemToCartItem(addCartItem);
        cartDomainService.initiateCartItem(cartItem, product);
        Cart cart = cartRepository.saveCartItem(extractUsernameFromJwt(token), cartItem);
        cartDomainService.calculateCartPrice(cart);
        return cartDataMapper.cartToCartDto(cart);
    }

    @Override
    public CartDto deleteCartItem(String token, UUID cartItemId) {
        Cart cart = cartRepository.deleteCartItem(extractUsernameFromJwt(token), cartItemId);
        cartDomainService.calculateCartPrice(cart);
        return cartDataMapper.cartToCartDto(cart);
    }
    @Override
    public CartDto getCart(String token) {
        Cart cart = cartRepository.getCart(extractUsernameFromJwt(token));
        cartDomainService.calculateCartPrice(cart);
        return cartDataMapper.cartToCartDto(cart);
    }

    @Override
    public CartDto updateCartItem(String token, UpdateCartItem updateCartItem) {
        CartItem cartItem = cartRepository.getCartItem(extractUsernameFromJwt(token), updateCartItem.getCartItemId());
        Product product = productRepository.getProduct(cartItem.getProductId());
        cartDomainService.updateCartItem(cartItem, product, updateCartItem.getQuantity());
        Cart cart = cartRepository.saveCartItem(extractUsernameFromJwt(token), cartItem);
        cartDomainService.calculateCartPrice(cart);
        return cartDataMapper.cartToCartDto(cart);
    }

    @Override
    public CartDto decreaseCartItem(String token, UUID cartItemID) {
        CartItem cartItem = cartRepository.getCartItem(extractUsernameFromJwt(token), cartItemID);
        Product product = productRepository.getProduct(cartItem.getProductId());
        cartDomainService.decreaseCartItem(cartItem, product);
        Cart cart = cartRepository.saveCartItem(extractUsernameFromJwt(token), cartItem);
        cartDomainService.calculateCartPrice(cart);
        return cartDataMapper.cartToCartDto(cart);
    }

    private String extractUsernameFromJwt(String token){
        return jwtService.extractUsername(token.substring(7));
    }

}
