package com.ecommerce.auth.user.domain;

import com.ecommerce.auth.token.Token;
import com.ecommerce.cart.domain.Cart;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class User {

    private UUID id;
    private String name;
    private final String email;

    @Setter
    private String password;
    private UserRole role;
    private boolean isActive;

    @Setter
    private long createdAt;
    private List<UserAddress> addresses;
    private List<Token> tokens;
    private Cart cart;

    public void initiate(){
        id = UUID.randomUUID();
        role = UserRole.USER;
        isActive = true;
        tokens = new ArrayList<>();
        addresses = new ArrayList<>();
        cart = Cart.builder()
                .userId(id)
                .build();
    }

}
