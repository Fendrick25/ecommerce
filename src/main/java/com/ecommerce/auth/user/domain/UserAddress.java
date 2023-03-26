package com.ecommerce.auth.user.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Getter
@Builder
@RequiredArgsConstructor
public class UserAddress {
    private UUID id;
    private UUID userId;
    private final String postalCode;
    private final String city;
    private final String address;
}
