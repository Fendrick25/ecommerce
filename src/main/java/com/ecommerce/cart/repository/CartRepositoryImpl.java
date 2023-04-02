package com.ecommerce.cart.repository;

import com.ecommerce.cart.domain.Cart;
import com.ecommerce.cart.domain.CartItem;
import com.ecommerce.cart.entity.CartItemEntity;
import com.ecommerce.cart.mapper.CartDataMapper;
import com.ecommerce.exception.InvalidRequestException;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.product.entity.ProductEntity;
import com.ecommerce.product.repository.ProductJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CartRepositoryImpl implements CartRepository{
    private final CartItemJpaRepository cartItemJpaRepository;
    private final CartJpaRepository cartJpaRepository;
    private final ProductJpaRepository productJpaRepository;
    private final CartDataMapper cartDataMapper;

    @Override
    public Cart getCart(String userEmail) {
        return cartDataMapper.cartEntityToCart(cartJpaRepository.findByUserEmail(userEmail));
    }

    @Override
    public void addCartItem(String userEmail, CartItem cartItem) {
        ProductEntity productEntity = productJpaRepository.findById(cartItem.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product with id: " + cartItem.getProductId() + " not found"));

        int availableStock = productEntity.getStock();
        int requestedStock = cartItem.getQuantity();
        if(availableStock < requestedStock)
            throw new InvalidRequestException("Not enough product stock, current stock: " + availableStock + ", requested stock: " + requestedStock);

        Optional<CartItemEntity> optionalCartItem = cartItemJpaRepository.findByProductId(cartItem.getProductId());
        if(optionalCartItem.isPresent()){
            CartItemEntity currentCartItem = optionalCartItem.get();
            int totalRequestedStock = currentCartItem.getQuantity() + cartItem.getQuantity();
            if(availableStock < totalRequestedStock)
                throw new InvalidRequestException("Not enough product stock, current stock: " + availableStock + ", requested total stock: " + totalRequestedStock);

            currentCartItem.setQuantity(totalRequestedStock);
            cartItemJpaRepository.save(currentCartItem);
        }else{
            CartItemEntity cartItemEntity = cartDataMapper.cartItemToCartItemEntity(cartItem);
            cartItemEntity.setCart(cartJpaRepository.findByUserEmail(userEmail));
            cartItemJpaRepository.save(cartItemEntity);
        }
    }

    @Override
    public void deleteCartItem(String userEmail, UUID cartItemId) {
        cartJpaRepository.findByUserEmail(userEmail).getItems()
                .stream()
                .filter(item -> item.getId().equals(cartItemId))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Cart Item with id: " + cartItemId + "on user with id: " + userEmail + " not found"));

        cartItemJpaRepository.deleteById(cartItemId);
    }
}
