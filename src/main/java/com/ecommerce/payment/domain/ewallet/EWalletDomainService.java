package com.ecommerce.payment.domain.ewallet;

import java.math.BigDecimal;

public interface EWalletDomainService {
    void initiate(EWallet eWallet);
    EWalletLog decreaseBalance(EWallet eWallet, BigDecimal amount);
}
