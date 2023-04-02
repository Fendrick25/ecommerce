package com.ecommerce.exception;


import com.ecommerce.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class EcommerceException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ApiError> handleException(Exception exception) {
        List<String> details = new ArrayList<>();
        details.add(exception.getMessage());

        ApiError err = ApiError.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message("An error occurred")
                .errors(details)
                .build();

        return new ResponseEntity<>(err, err.getStatus());
    }


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleResourceNotFoundException(ResourceNotFoundException exception){
        List<String> details = new ArrayList<>();
        details.add(exception.getMessage());

        ApiError err = ApiError.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST)
                .message("Data not found")
                .errors(details)
                .build();

        return new ResponseEntity<>(err, err.getStatus());
    }

    @ExceptionHandler(InvalidStateException.class)
    public ResponseEntity<ApiError> handleInvalidStateException(InvalidStateException exception){
        List<String> details = new ArrayList<>();
        details.add(exception.getMessage());

        ApiError err = ApiError.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST)
                .message("Invalid State")
                .errors(details)
                .build();

        return new ResponseEntity<>(err, err.getStatus());
    }

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<ApiError> handleInvalidRequestException(InvalidRequestException exception){
        List<String> details = new ArrayList<>();
        details.add(exception.getMessage());

        ApiError err = ApiError.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST)
                .message("Invalid Request")
                .errors(details)
                .build();

        return new ResponseEntity<>(err, err.getStatus());
    }

    @ExceptionHandler(ExpiredJwtTokenException.class)
    public ResponseEntity<ApiError> handleExpiredJwtTokenException(ExpiredJwtTokenException exception){
        List<String> details = new ArrayList<>();
        details.add(exception.getMessage());

        ApiError err = ApiError.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.FORBIDDEN)
                .message("Token Expired")
                .errors(details )
                .build();

        return new ResponseEntity<>(err, err.getStatus());
    }
}
