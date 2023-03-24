package com.ecommerce;

import com.ecommerce.product.domain.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = ProductTestConfiguration.class)
public class ProductDomainTest {

    @Autowired
    private ProductDomainService productDomainService;
    private Product product;

    @BeforeEach
    public void setUp() {
        product = Product.builder()
                .name("Test Product")
                .description("This is a test product")
                .price(BigDecimal.TEN)
                .weight(1.0)
                .stock(10)
                .status(ProductStatus.NOT_ACTIVE)
                .category(ProductCategory.NO_CATEGORY)
                .build();
    }

    @Test
    public void testInitiateProduct() {
        productDomainService.initiate(product);

        assertNotNull(product.getId());
        assertEquals(ProductStatus.NOT_ACTIVE, product.getStatus());
        assertEquals(ProductCategory.NO_CATEGORY, product.getCategory());
        assertEquals(Instant.parse("2023-03-11T10:00:00Z").getEpochSecond(), product.getCreatedAt());
    }

    @Test
    public void testUpdateStockToZero() {
        productDomainService.updateStock(product, 0);
        assertEquals(ProductStatus.NOT_ACTIVE, product.getStatus());
    }
}
