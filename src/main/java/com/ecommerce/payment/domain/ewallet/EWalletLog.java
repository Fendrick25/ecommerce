package com.ecommerce.payment.domain.ewallet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;


@Getter
@Builder
@AllArgsConstructor
public class EWalletLog {
    private UUID id;
    private String log;
    private long createdAt;

    public void initiate(){
        id = UUID.randomUUID();
    }
}
