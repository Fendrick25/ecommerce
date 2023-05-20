package com.ecommerce.payment.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class PaymentLog {
    private UUID id;
    private UUID paymentId;
    private String log;
    private long createdAt;

    public void initiate(){
        id = UUID.randomUUID();
    }
}
