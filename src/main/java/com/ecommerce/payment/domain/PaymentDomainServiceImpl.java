package com.ecommerce.payment.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Clock;

@Component
@RequiredArgsConstructor
public class PaymentDomainServiceImpl implements PaymentDomainService{

    private final Clock clock;
    @Override
    public PaymentLog initiate(Payment payment, PaymentType paymentType) {

        if(paymentType.equals(PaymentType.E_WALLET))
            payment.setPaymentStatus(PaymentStatus.PAID);

        if(paymentType.equals(PaymentType.TRANSFER))
            payment.setPaymentStatus(PaymentStatus.NOT_PAID);

        return createLog(payment, paymentType);
    }

    private PaymentLog createLog(Payment payment, PaymentType paymentType){
        PaymentLog log =  PaymentLog.builder()
                .paymentId(payment.getId())
                .createdAt(clock.instant().getEpochSecond())
                .log("Payment created with payment type: " + paymentType.toString() + " status: " + payment.getPaymentStatus().toString())
                .build();

        log.initiate();
        return log;
    }
}
