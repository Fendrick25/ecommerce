package com.ecommerce.payment.domain;

public interface PaymentDomainService {
    PaymentLog initiate(Payment payment, PaymentType paymentType);

}
