package com.example.booking.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class InsufficentBedsNumberException extends RuntimeException {
    public InsufficentBedsNumberException(String message) {
        super(message);
    }
}
