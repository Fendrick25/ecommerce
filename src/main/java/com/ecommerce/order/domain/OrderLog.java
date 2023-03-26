package com.ecommerce.order.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class OrderLog {
    private UUID id;
    private final String log;
    @Setter
    private long createdAt;

    void initiate(){
        id = UUID.randomUUID();
    }

}
