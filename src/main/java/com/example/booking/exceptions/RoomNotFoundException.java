package com.example.booking.exceptions;

import org.springframework.http.HttpStatusCode;

public class RoomNotFoundException extends StatusException {

    public RoomNotFoundException(HttpStatusCode httpStatusCode, String message) {
        super(httpStatusCode, message);
    }
}
