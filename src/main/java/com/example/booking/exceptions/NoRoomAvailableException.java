package com.example.booking.exceptions;

import org.springframework.http.HttpStatusCode;

public class NoRoomAvailableException extends StatusException {

    public NoRoomAvailableException(HttpStatusCode httpStatusCode, String message) {
        super(httpStatusCode, message);
    }
}
