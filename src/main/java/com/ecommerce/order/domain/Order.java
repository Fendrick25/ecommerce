package com.ecommerce.order.domain;

import com.ecommerce.auth.user.domain.UserAddress;
import com.ecommerce.exception.InvalidStateException;
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
public class Order {
    private UUID id;
    private final List<OrderItem> items;
    private BigDecimal total;
    private final UserAddress address;

    @Setter
    private long createdAt;
    private OrderStatus status;
    private List<OrderLog> logs;

    void initiate(){
        id = UUID.randomUUID();
        status = OrderStatus.NOT_PAID;
        logs = new ArrayList<>();
    }

    void addOrderLog(OrderLog log){
        logs.add(log);
    }

    void calculateTotal(){
        total = items.stream()
                .map(OrderItem::calculateSubTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    void payOrder(BigDecimal paymentTotal){
        if(!status.equals(OrderStatus.NOT_PAID))
            throw new InvalidStateException("Not in correct state for payment");
        if(!total.equals(paymentTotal))
            throw new InvalidStateException("Money not equal");

        status = OrderStatus.PAID;
    }

    void cancelOrder(){
        if(!status.equals(OrderStatus.NOT_PAID))
            throw new InvalidStateException("Not in correct state for cancel order");

        status = OrderStatus.CANCELLED;
    }

    void deliverOrder(){
        if(!status.equals(OrderStatus.PAID))
            throw new InvalidStateException("Not in correct state for deliver order");

        status = OrderStatus.ON_DELIVERY;
    }

    void orderDelivered(){
        if(!status.equals(OrderStatus.ON_DELIVERY))
            throw new InvalidStateException("Not in correct state for delivered order");

        status = OrderStatus.DELIVERED;
    }

    void finishOrder(){
        if(!status.equals(OrderStatus.DELIVERED))
            throw new InvalidStateException("Not in correct state for finish order");

        status = OrderStatus.FINISHED;
    }
}
