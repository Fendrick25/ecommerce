package com.ecommerce.auth.user.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class UserAddress {
    private UUID id;
    private String name;
    private final String postalCode;
    private final String city;
    private final String address;

    public void initiate(){
        id = UUID.randomUUID();
    }
}
