package com.ecommerce.auth.user.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table
@Entity(name =  "user_addresses")
public class UserAddressEntity {
    @Id
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String postalCode;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false, length = 1000)
    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity user;
}
