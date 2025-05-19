package com.janissary.fundraising.exception;

import lombok.Getter;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Getter
public class ApiException extends RuntimeException {
    private final ZonedDateTime timestamp;

    public ApiException(String message) {
        super(message);
        this.timestamp = ZonedDateTime.now(ZoneId.of("UTC"));
    }
}
