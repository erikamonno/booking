package com.example.booking.exceptions;

import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(exception = StatusException.class)
    public ResponseEntity<ProblemDetail> handleStatusException(StatusException e) {
        var problem = ProblemDetail.forStatusAndDetail(e.getHttpStatusCode(), e.getMessage());
        return ResponseEntity.status(e.getHttpStatusCode()).body(problem);
    }
}
