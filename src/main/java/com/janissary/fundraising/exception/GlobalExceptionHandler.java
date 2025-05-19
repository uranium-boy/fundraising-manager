package com.janissary.fundraising.exception;

import com.janissary.fundraising.dto.response.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private ResponseEntity<ErrorResponse> buildErrorResponse (
            ApiException e,
            HttpStatus status,
            HttpServletRequest request
    ) {
        ErrorResponse response = new ErrorResponse(
                status.value(),
                status.name(),
                e.getMessage(),
                request.getRequestURI(),
                e.getTimestamp()
        );

        return new ResponseEntity<>(response, status);
    }

    @ExceptionHandler(value = {UnacceptedCurrencyException.class})
    public ResponseEntity<ErrorResponse> handleUnacceptedCurrencyException(
            UnacceptedCurrencyException e,
            HttpServletRequest request
    ) {
        return buildErrorResponse(e, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(EventNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEventNotFoundException(
            EventNotFoundException e,
            HttpServletRequest request
    ) {
        return buildErrorResponse(e, HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(CollectionBoxNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCollectionBoxNotFoundException(
            CollectionBoxNotFoundException e,
            HttpServletRequest request
    ) {
        return buildErrorResponse(e, HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(CollectionBoxNotAssignedException.class)
    public ResponseEntity<ErrorResponse> handleCollectionBoxNotAssignedException(
            CollectionBoxNotAssignedException e,
            HttpServletRequest request
    ) {
        return buildErrorResponse(e, HttpStatus.BAD_REQUEST, request);
    }
}
