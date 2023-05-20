package com.ecommerce.payment.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class Payment {
    private UUID id;
    private UUID userId;

    @Setter
    private long createdAt;
    private BigDecimal amount;
    private List<PaymentLog> logs;
    private PaymentType paymentType;

    @Setter
    private PaymentStatus paymentStatus;

    public void initiate(){
        id = UUID.randomUUID();
        logs = new ArrayList<>();
    }
}
