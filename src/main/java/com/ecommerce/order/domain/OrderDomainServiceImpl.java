package com.ecommerce.order.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Clock;

@Component
@RequiredArgsConstructor
public class OrderDomainServiceImpl implements OrderDomainService{
    private final Clock clock;
    @Override
    public OrderLog initiate(Order order) {
        order.initiate();
        order.calculateTotal();
        order.setCreatedAt(clock.instant().getEpochSecond());

        OrderLog orderLog = OrderLog.builder()
                .log("ORDER CREATED")
                .createdAt(clock.instant().getEpochSecond())
                .build();
        orderLog.initiate();
        return orderLog;
    }

    @Override
    public OrderLog payOrder(Order order, BigDecimal paymentTotal) {
        order.payOrder(paymentTotal);
        OrderLog orderLog = OrderLog.builder()
                .log("ORDER PAID")
                .createdAt(clock.instant().getEpochSecond())
                .build();
        orderLog.initiate();
        return orderLog;
    }

    @Override
    public OrderLog cancelOrder(Order order) {
        order.cancelOrder();
        OrderLog orderLog = OrderLog.builder()
                .log("ORDER CANCELLED")
                .createdAt(clock.instant().getEpochSecond())
                .build();
        orderLog.initiate();
        return orderLog;
    }

    @Override
    public OrderLog deliverOrder(Order order) {
        order.deliverOrder();
        OrderLog orderLog = OrderLog.builder()
                .log("ORDER ON DELIVERY")
                .createdAt(clock.instant().getEpochSecond())
                .build();
        orderLog.initiate();
        return orderLog;
    }

    @Override
    public OrderLog orderDelivered(Order order) {
        order.orderDelivered();
        OrderLog orderLog = OrderLog.builder()
                .log("ORDER DELIVERED")
                .createdAt(clock.instant().getEpochSecond())
                .build();
        orderLog.initiate();
        return orderLog;
    }

    @Override
    public OrderLog finishOrder(Order order) {
        order.finishOrder();
        OrderLog orderLog = OrderLog.builder()
                .log("ORDER FINISHED")
                .createdAt(clock.instant().getEpochSecond())
                .build();
        orderLog.initiate();
        return orderLog;
    }
}
