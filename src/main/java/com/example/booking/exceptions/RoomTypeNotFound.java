package com.example.booking.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RoomTypeNotFound extends RuntimeException{
    public RoomTypeNotFound(String message) {
        super(message);
    }
}
