package com.example.booking.exceptions;

import org.springframework.http.HttpStatusCode;

public class BookingNotFoundException extends StatusException {

    public BookingNotFoundException(HttpStatusCode httpStatusCode, String message) {
        super(httpStatusCode, message);
    }
}
