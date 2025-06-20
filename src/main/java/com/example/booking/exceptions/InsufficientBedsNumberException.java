package com.example.booking.exceptions;

import org.springframework.http.HttpStatusCode;

public class InsufficientBedsNumberException extends StatusException {

    public InsufficientBedsNumberException(HttpStatusCode httpStatusCode, String message) {
        super(httpStatusCode, message);
    }
}
