package com.ecommerce.auth.user.repository;

import com.ecommerce.auth.user.entity.UserAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserAddressJpaRepository extends JpaRepository<UserAddressEntity, UUID> {
}
