package com.ecommerce;

import com.ecommerce.cart.domain.CartDomainService;
import com.ecommerce.cart.domain.CartDomainServiceImpl;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = "com.ecommerce.cart.domain")
public class CartTestConfiguration {

    @Bean
    public CartDomainService cartDomainService(){
        return new CartDomainServiceImpl();
    }
}
