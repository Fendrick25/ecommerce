package com.ecommerce.cart.mapper;

import com.ecommerce.auth.user.entity.UserEntity;
import com.ecommerce.cart.domain.Cart;
import com.ecommerce.cart.domain.CartItem;
import com.ecommerce.cart.dto.AddCartItem;
import com.ecommerce.cart.dto.CartDto;
import com.ecommerce.cart.dto.CartItemDto;
import com.ecommerce.cart.entity.CartEntity;
import com.ecommerce.cart.entity.CartItemEntity;
import com.ecommerce.product.entity.ProductEntity;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CartDataMapper {
    public CartItem addCartItemToCartItem(AddCartItem addCartItem){
        return CartItem.builder()
                .productId(addCartItem.getProductId())
                .quantity(addCartItem.getQuantity())
                .build();
    }

    public CartDto cartToCartDto(Cart cart){
        return CartDto.builder()
                .id(cart.getId())
                .userId(cart.getUserId())
                .total(cart.getTotal())
                .items(cart.getItems().stream()
                        .map(this::cartItemToCartItemDto)
                        .collect(Collectors.toList()))
                .build();
    }

    public CartItemDto cartItemToCartItemDto(CartItem cartItem){
        return CartItemDto.builder()
                .id(cartItem.getId())
                .cartId(cartItem.getCartId())
                .price(cartItem.getPrice())
                .quantity(cartItem.getQuantity())
                .subTotal(cartItem.getSubTotal())
                .productId(cartItem.getProductId())
                .build();
    }

    public CartEntity cartToCartEntity(Cart cart){
        return CartEntity.builder()
                .id(cart.getId())
                .user(UserEntity.builder()
                        .id(cart.getUserId())
                        .build())
                .items(cart.getItems().stream()
                        .map(this::cartItemToCartItemEntity)
                        .collect(Collectors.toList()))
                .build();
    }

    public CartItemEntity cartItemToCartItemEntity(CartItem cartItem){
        return CartItemEntity.builder()
                .id(cartItem.getId())
                .quantity(cartItem.getQuantity())
                .product(ProductEntity.builder()
                        .id(cartItem.getProductId())
                        .build())
                .cart(CartEntity.builder()
                        .id(cartItem.getCartId())
                        .build())
                .build();
    }

}
