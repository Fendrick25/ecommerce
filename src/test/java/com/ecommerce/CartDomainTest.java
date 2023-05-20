package com.ecommerce;

import com.ecommerce.cart.domain.Cart;
import com.ecommerce.cart.domain.CartDomainService;
import com.ecommerce.cart.domain.CartItem;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = CartTestConfiguration.class)
public class CartDomainTest {

    @Autowired
    private CartDomainService cartDomainService;

    @Test
    public void testInitiateCart() {
        Cart cart = Cart.builder()
                .userId(UUID.randomUUID())
                .build();

        cartDomainService.initiateCart(cart);

        assertNotNull(cart.getId());
        assertNotNull(cart.getItems());
    }

    @Test
    public void testInitiateCartItem() {
        CartItem cartItem = CartItem.builder()
                .productId(UUID.randomUUID())
                .productName("Test Product")
                .price(BigDecimal.valueOf(100))
                .quantity(2)
                .imageUrl("http://test.com/image.jpg")
                .build();

        cartDomainService.initiateCartItem(cartItem);

        assertNotNull(cartItem.getId());
    }

    @Test
    public void testCalculateCartPrice() {
        List<CartItem> items = new ArrayList<>();
        items.add(CartItem.builder()
                .productId(UUID.randomUUID())
                .productName("Test Product 1")
                .price(BigDecimal.valueOf(100))
                .quantity(2)
                .imageUrl("http://test.com/image1.jpg")
                .build());
        items.add(CartItem.builder()
                .productId(UUID.randomUUID())
                .productName("Test Product 2")
                .price(BigDecimal.valueOf(50))
                .quantity(3)
                .imageUrl("http://test.com/image2.jpg")
                .build());

        Cart cart = Cart.builder()
                .userId(UUID.randomUUID())
                .items(items)
                .build();

        cartDomainService.calculateCartPrice(cart);

        assertEquals(BigDecimal.valueOf(350), cart.getTotal());
    }
}
