package com.ecommerce.cart.repository;

import com.ecommerce.cart.domain.Cart;
import com.ecommerce.cart.domain.CartItem;
import com.ecommerce.cart.entity.CartEntity;
import com.ecommerce.cart.entity.CartItemEntity;
import com.ecommerce.cart.mapper.CartDataMapper;
import com.ecommerce.exception.ResourceNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CartRepositoryImpl implements CartRepository{
    private final CartItemJpaRepository cartItemJpaRepository;
    private final CartJpaRepository cartJpaRepository;
    private final CartDataMapper cartDataMapper;

    @Override
    public Cart getCart(String userEmail) {
        return cartDataMapper.cartEntityToCart(findCartByEmail(userEmail));
    }

    @Override
    public Cart saveCartItem(String userEmail, CartItem cartItem) {
        CartItemEntity cartItemEntity = cartDataMapper.cartItemToCartItemEntity(cartItem);
        cartItemEntity.setCart(findCartByEmail(userEmail));
        cartItemJpaRepository.save(cartItemEntity);
        return cartDataMapper.cartEntityToCart(findCartByEmail(userEmail));
    }

    @Override
    public Cart deleteCartItem(String userEmail, UUID cartItemId) {
        findCartItemByEmail(userEmail, cartItemId);
        cartItemJpaRepository.deleteById(cartItemId);
        return cartDataMapper.cartEntityToCart(findCartByEmail(userEmail));
    }

    @Override
    public CartItem getCartItem(String userEmail, UUID cartItemId) {
        return cartDataMapper.cartItemEntityToCartItem(findCartItemByEmail(userEmail, cartItemId));
    }

    private CartEntity findCartByEmail(String email){
        return cartJpaRepository.findByUserEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found for user with email: " + email));
    }

    private CartItemEntity findCartItemByEmail(String userEmail, UUID cartItemId){
        return cartItemJpaRepository.findByCart_User_EmailAndId(userEmail, cartItemId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart item with id: " + cartItemId + " not found"));
    }
}
