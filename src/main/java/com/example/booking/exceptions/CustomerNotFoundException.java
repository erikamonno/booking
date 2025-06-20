package com.example.booking.exceptions;

import org.springframework.http.HttpStatusCode;

public class CustomerNotFoundException extends StatusException {

    public CustomerNotFoundException(HttpStatusCode httpStatusCode, String message) {
        super(httpStatusCode, message);
    }
}
