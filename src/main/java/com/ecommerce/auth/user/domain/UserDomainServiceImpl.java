package com.ecommerce.auth.user.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Clock;

@Component
@RequiredArgsConstructor
public class UserDomainServiceImpl implements UserDomainService{
    private final Clock clock;

    @Override
    public void initiate(User user) {
        user.initiate();
        user.setCreatedAt(clock.instant().getEpochSecond());
    }

    @Override
    public void changePassword(User user, String password) {

    }

    @Override
    public void initiateAddress(UserAddress userAddress) {
        userAddress.initiate();
    }
}
