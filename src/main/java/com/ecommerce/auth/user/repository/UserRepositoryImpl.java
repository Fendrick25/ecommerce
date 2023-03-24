package com.ecommerce.auth.user.repository;

import com.ecommerce.auth.user.domain.User;
import com.ecommerce.auth.user.mapper.UserDataMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository{

    private final UserDataMapper userDataMapper;
    private final UserJpaRepository userJpaRepository;
    @Override
    public User findByEmail(String email) {
        return userDataMapper.userEntityToUser(userJpaRepository.findByEmail(email).
                orElseThrow(() -> new UsernameNotFoundException("User with email: " + email + " not found!")));
    }

    @Override
    public User saveUser(User user) {
        return userDataMapper.userEntityToUser(userJpaRepository.save(userDataMapper.userToUserEntity(user)));
    }
}
