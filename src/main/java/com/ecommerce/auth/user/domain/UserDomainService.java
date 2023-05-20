package com.ecommerce.auth.user.domain;

public interface UserDomainService {
    void initiate(User user);
    void changePassword(User user, String password);
    void initiateAddress(UserAddress userAddress);
}
