package com.ecommerce.auth.token;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class Token {
    private Integer id;
    private final UUID userId;
    private final String token;
    private TokenType type;

    @Setter
    private boolean revoked;
    @Setter
    private boolean expired;

    public void initiate(){
        type = TokenType.BEARER;
        expired = false;
        revoked = false;
    }
}
