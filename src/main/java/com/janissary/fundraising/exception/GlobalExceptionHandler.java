package com.janissary.fundraising.exception;

import com.janissary.fundraising.dto.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {UnacceptedCurrencyException.class})
    public ResponseEntity<ErrorResponse> handleCurrencyNotFoundException(UnacceptedCurrencyException e) {
        // TODO: add path field from HttpServerlet
        ErrorResponse response = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.name(),
                e.getMessage(),
                e.getTimestamp()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
