package com.ecommerce.auth.token;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TokenJpaRepository extends JpaRepository<TokenEntity, Integer> {
    List<TokenEntity> findByUser_IdAndExpiredFalseOrRevokedFalse(UUID userId);
    Optional<TokenEntity> findByToken(String token);
}
