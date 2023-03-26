package com.ecommerce.order.domain;

import java.math.BigDecimal;

public interface OrderDomainService {
    void initiate(Order order);
    void payOrder(Order order, BigDecimal paymentTotal);
    void cancelOrder(Order order);
    void deliverOrder(Order order);
    void orderDelivered(Order order);
    void finishOrder(Order order);
}
