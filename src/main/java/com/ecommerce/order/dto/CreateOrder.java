package com.ecommerce.order.dto;

import com.ecommerce.payment.domain.PaymentType;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@Getter
@Builder
@RequiredArgsConstructor
public class CreateOrder {
    private final List<UUID> cartItemIds;
    private final UUID addressId;
    private final PaymentType paymentType;
}
