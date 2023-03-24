package com.ecommerce.auth.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class RegisterRequest {
    private final String name;
    private final String email;
    private final String password;
}