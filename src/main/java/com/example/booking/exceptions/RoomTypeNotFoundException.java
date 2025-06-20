package com.example.booking.exceptions;

import org.springframework.http.HttpStatusCode;

public class RoomTypeNotFoundException extends StatusException {

    public RoomTypeNotFoundException(HttpStatusCode httpStatusCode, String message) {
        super(httpStatusCode, message);
    }
}
