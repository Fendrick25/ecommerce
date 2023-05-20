package com.ecommerce.payment.domain.ewallet;

import com.ecommerce.exception.BalanceNotEnoughException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class EWallet {
    private UUID id;
    private final UUID userId;
    private BigDecimal balance;
    private List<EWalletLog> logs;

    public void initiate(){
        id = UUID.randomUUID();
        balance = BigDecimal.ZERO;
        logs = new ArrayList<>();
    }

    public void decreaseBalance(BigDecimal amount){
        if(balance.compareTo(amount) < 0)
            throw new BalanceNotEnoughException("Insufficient balance! current balance: " + balance + " , amount: " + amount);

        balance = balance.subtract(balance);
    }
}
