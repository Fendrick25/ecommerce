package com.ecommerce.cart.controller;

import com.ecommerce.Data;
import com.ecommerce.cart.dto.AddCartItem;
import com.ecommerce.cart.dto.CartDto;
import com.ecommerce.cart.dto.UpdateCartItem;
import com.ecommerce.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/carts")
public class CartController {

    private final CartService cartService;
    @PostMapping("/items")
    public ResponseEntity<Data<CartDto>> addCartItem(@RequestHeader(name = "Authorization") String token, @RequestBody AddCartItem addCartItem){
        return new ResponseEntity<>(new Data<>(cartService.addCartItem(token, addCartItem)) ,HttpStatus.CREATED);
    }

    @DeleteMapping("/items/{cartItemId}")
    public ResponseEntity<Data<CartDto>> deleteCartItem(@RequestHeader(name = "Authorization") String token, @PathVariable("cartItemId") UUID cartItemId){
        return new ResponseEntity<>(new Data<>(cartService.deleteCartItem(token, cartItemId)), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Data<CartDto>> getCart(@RequestHeader(name = "Authorization") String token){
        return new ResponseEntity<>(new Data<>(cartService.getCart(token)), HttpStatus.OK);
    }

    @PutMapping("/items")
    public ResponseEntity<Data<CartDto>> updateCartItem(@RequestHeader(name = "Authorization") String token, @RequestBody UpdateCartItem updateCartItem){
        return new ResponseEntity<>(new Data<>(cartService.updateCartItem(token, updateCartItem)), HttpStatus.OK);
    }

    @PutMapping("/items/{cartItemId}")
    public ResponseEntity<Data<CartDto>> decreaseCartItem(@RequestHeader(name = "Authorization") String token, @PathVariable("cartItemId") UUID cartItemId){
        return new ResponseEntity<>(new Data<>(cartService.decreaseCartItem(token, cartItemId)), HttpStatus.OK);
    }

}
