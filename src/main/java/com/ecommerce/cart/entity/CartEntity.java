package com.ecommerce.cart.entity;

import com.ecommerce.auth.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "carts")
public class CartEntity {
    @Id
    private UUID id;
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<CartItemEntity> items;

    @OneToOne(mappedBy = "cart")
    private UserEntity user;

}