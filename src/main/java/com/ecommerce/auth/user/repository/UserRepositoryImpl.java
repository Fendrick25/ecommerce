package com.ecommerce.auth.user.repository;

import com.ecommerce.auth.user.domain.User;
import com.ecommerce.auth.user.entity.UserEntity;
import com.ecommerce.auth.user.mapper.UserDataMapper;
import com.ecommerce.cart.repository.CartJpaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository{

    private final UserDataMapper userDataMapper;
    private final UserJpaRepository userJpaRepository;
    private final CartJpaRepository cartJpaRepository;
    @Override
    public User findByEmail(String email) {
        return userDataMapper.userEntityToUser(userJpaRepository.findByEmail(email).
                orElseThrow(() -> new UsernameNotFoundException("User with email: " + email + " not found!")));
    }

    @Override
    public User saveUser(User user) {
        UserEntity userEntity = userDataMapper.userToUserEntity(user);
        userEntity.getCart().setUser(userEntity);
        return userDataMapper.userEntityToUser(userJpaRepository.save(userEntity));
    }
}
