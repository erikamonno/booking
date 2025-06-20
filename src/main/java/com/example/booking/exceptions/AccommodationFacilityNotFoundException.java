package com.example.booking.exceptions;

import org.springframework.http.HttpStatusCode;

public class AccommodationFacilityNotFoundException extends StatusException {

    public AccommodationFacilityNotFoundException(HttpStatusCode httpStatusCode, String message) {
        super(httpStatusCode, message);
    }
}
