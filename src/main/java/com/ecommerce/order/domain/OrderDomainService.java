package com.ecommerce.order.domain;

import java.math.BigDecimal;

public interface OrderDomainService {
    OrderLog initiate(Order order);
    OrderLog payOrder(Order order, BigDecimal paymentTotal);
    OrderLog cancelOrder(Order order);
    OrderLog deliverOrder(Order order);
    OrderLog orderDelivered(Order order);
    OrderLog finishOrder(Order order);
}
