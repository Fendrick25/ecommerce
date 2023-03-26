package com.ecommerce.auth.service;

import com.ecommerce.auth.dto.AuthenticationRequest;
import com.ecommerce.auth.dto.AuthenticationResponse;
import com.ecommerce.auth.dto.RegisterRequest;
import com.ecommerce.auth.token.Token;
import com.ecommerce.auth.token.TokenRepository;
import com.ecommerce.auth.token.TokenType;
import com.ecommerce.auth.user.domain.User;
import com.ecommerce.auth.user.domain.UserDomainService;
import com.ecommerce.auth.user.mapper.UserDataMapper;
import com.ecommerce.auth.user.repository.UserRepository;
import com.ecommerce.cart.domain.CartDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserDomainService userDomainService;
    private final CartDomainService cartDomainService;
    private final UserDataMapper userDataMapper;

    public AuthenticationResponse register(RegisterRequest registerRequest) {

        User user = userDataMapper.registerRequestToUser(registerRequest);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDomainService.initiate(user);
        cartDomainService.initiateCart(user.getCart());
        User savedUser = userRepository.saveUser(user);
        String jwtToken = jwtService.generateToken(savedUser.getEmail());
        saveUserToken(savedUser, jwtToken);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        User user = userRepository.findByEmail(request.getEmail());
        String jwtToken = jwtService.generateToken(user.getEmail());
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    private void saveUserToken(User user, String jwtToken) {
        tokenRepository.saveToken(Token.builder()
                .userId(user.getId())
                .token(jwtToken)
                .type(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build());
    }

    private void revokeAllUserTokens(User user) {
        List<Token> validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAllToken(validUserTokens);
    }
}
