package com.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ExpiredJwtTokenException extends RuntimeException{
    public ExpiredJwtTokenException(String message) {
        super(message);
    }
}
