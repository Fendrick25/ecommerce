package com.ecommerce.payment.domain.ewallet;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Clock;

@Component
@RequiredArgsConstructor
public class EWalletDomainServiceImpl implements EWalletDomainService{
    private final Clock clock;
    @Override
    public void initiate(EWallet eWallet) {
        eWallet.initiate();
    }

    @Override
    public EWalletLog decreaseBalance(EWallet eWallet, BigDecimal amount) {
        eWallet.decreaseBalance(amount);
        EWalletLog log = EWalletLog.builder()
                .log("balanced decreased by: "+ amount)
                .createdAt(clock.instant().getEpochSecond())
                .build();
        log.initiate();
        return log;
    }
}
