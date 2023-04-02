package com.ecommerce.cart.controller;

import com.ecommerce.Data;
import com.ecommerce.cart.dto.AddCartItem;
import com.ecommerce.cart.dto.CartDto;
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
    @PostMapping
    public ResponseEntity<Void> addCartItem(@RequestHeader(name = "Authorization") String token, @RequestBody AddCartItem addCartItem){
        cartService.addCartItem(token, addCartItem);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{cartItemId}")
    public ResponseEntity<Void> deleteCartItem(@RequestHeader(name = "Authorization") String token, @PathVariable("cartItemId") UUID cartItemId){
        cartService.deleteCartItem(token, cartItemId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Data<CartDto>> getCart(@RequestHeader(name = "Authorization") String token){
        return new ResponseEntity<>(new Data<>(cartService.getCart(token)), HttpStatus.OK);
    }
}
