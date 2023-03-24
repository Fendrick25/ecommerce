package com.ecommerce.auth.user.repository;

import com.ecommerce.auth.user.domain.User;

import java.util.Optional;

public interface UserRepository {
    User findByEmail (String email);
    User saveUser(User user);
}