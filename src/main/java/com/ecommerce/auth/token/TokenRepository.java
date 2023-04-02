package com.ecommerce.auth.token;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TokenRepository {

    Optional<Token> findByToken(String token);
    List<Token> findAllValidTokenByUser(UUID userId);
    void saveToken(Token token);
    void saveAllToken(List<Token> tokens);
}
