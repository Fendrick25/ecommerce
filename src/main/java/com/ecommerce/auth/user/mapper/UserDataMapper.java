package com.ecommerce.auth.user.mapper;

import com.ecommerce.auth.dto.RegisterRequest;
import com.ecommerce.auth.token.Token;
import com.ecommerce.auth.token.TokenEntity;
import com.ecommerce.auth.user.domain.User;
import com.ecommerce.auth.user.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserDataMapper {
    public User registerRequestToUser(RegisterRequest registerRequest){
        return User.builder()
                .name(registerRequest.getName())
                .email(registerRequest.getEmail())
                .password(registerRequest.getPassword())
                .build();
    }

    public User userEntityToUser(UserEntity userEntity){
        return User.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .createdAt(userEntity.getCreatedAt())
                .role(userEntity.getRole())
                .isActive(userEntity.isActive())
                .tokens(userEntity.getTokens().stream()
                        .map(this::tokenEntityToToken)
                        .collect(Collectors.toList()))
                .build();
    }

    public UserEntity userToUserEntity(User user){
        return UserEntity.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .createdAt(user.getCreatedAt())
                .role(user.getRole())
                .isActive(user.isActive())
                .tokens(user.getTokens().stream()
                        .map(this::tokenToTokenEntity)
                        .collect(Collectors.toList()))
                .build();
    }

    public Token tokenEntityToToken(TokenEntity tokenEntity){
        return Token.builder()
                .id(tokenEntity.getId())
                .token(tokenEntity.getToken())
                .userId(tokenEntity.getUser().getId())
                .expired(tokenEntity.isExpired())
                .revoked(tokenEntity.isRevoked())
                .type(tokenEntity.getType())
                .build();
    }

    public TokenEntity tokenToTokenEntity(Token token){
        return TokenEntity.builder()
                .id(token.getId())
                .token(token.getToken())
                .user(UserEntity.builder()
                        .id(token.getUserId())
                        .build())
                .expired(token.isExpired())
                .revoked(token.isExpired())
                .type(token.getType())
                .build();
    }

}
