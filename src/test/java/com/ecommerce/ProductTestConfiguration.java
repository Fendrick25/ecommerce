package com.ecommerce;

import com.ecommerce.product.domain.ProductDomainService;
import com.ecommerce.product.domain.ProductDomainServiceImpl;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

@SpringBootApplication(scanBasePackages = "com.ecommerce.product")
public class ProductTestConfiguration {

    @Bean
    public Clock clock(){
        return Clock.fixed(Instant.parse("2023-03-11T10:00:00Z"), ZoneId.systemDefault());
    }

    @Bean
    public ProductDomainService productDomainService(){
        return new ProductDomainServiceImpl(clock());
    }


}
