package com.ecommerce.auth.token;

import com.ecommerce.auth.user.entity.UserEntity;
import com.ecommerce.auth.user.mapper.UserDataMapper;
import com.ecommerce.auth.user.repository.UserJpaRepository;
import com.ecommerce.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TokenRepositoryImpl implements TokenRepository{
    private final TokenJpaRepository tokenJpaRepository;
    private final UserJpaRepository userJpaRepository;
    private final UserDataMapper userDataMapper;

    @Override
    public Optional<Token> findByToken(String token) {
        return Optional.ofNullable(userDataMapper.tokenEntityToToken(tokenJpaRepository.findByToken(token).orElseThrow(() -> new ResourceNotFoundException("Token not found"))));
    }

    @Override
    public List<Token> findAllValidTokenByUser(UUID userId) {
        return tokenJpaRepository.findByUser_IdAndExpiredFalseOrRevokedFalse(userId).stream()
                .map(userDataMapper::tokenEntityToToken)
                .collect(Collectors.toList());
    }

    @Override
    public void saveToken(Token token) {
        TokenEntity tokenEntity = userDataMapper.tokenToTokenEntity(token);
        tokenEntity.setUser(userJpaRepository.findById(tokenEntity.getUser().getId()).orElseThrow(() -> new ResourceNotFoundException("User with id: " + tokenEntity.getUser().getId() + " not found!")));
        tokenJpaRepository.save(userDataMapper.tokenToTokenEntity(token));
    }

    @Override
    public void saveAllToken(List<Token> tokens) {
        List<TokenEntity> tokenEntities =  tokens.stream()
                .map(token -> {
                    TokenEntity tokenEntity = userDataMapper.tokenToTokenEntity(token);
                    UUID userId = token.getUserId();
                    UserEntity userEntity = userJpaRepository.findById(userId)
                            .orElseThrow(() -> new ResourceNotFoundException("User with id: " + userId + " not found!"));
                    tokenEntity.setUser(userEntity);
                    return tokenEntity;
                })
                .collect(Collectors.toList());
        tokenJpaRepository.saveAll(tokenEntities);
    }
}
