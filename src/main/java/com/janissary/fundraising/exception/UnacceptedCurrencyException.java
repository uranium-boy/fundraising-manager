package com.janissary.fundraising.exception;

import lombok.Getter;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Getter
public class UnacceptedCurrencyException extends RuntimeException {
    private final ZonedDateTime timestamp;

    public UnacceptedCurrencyException(
            String currencyCode
    ) {
        super(String.format("%s is not an accepted currency.", currencyCode));
        this.timestamp = ZonedDateTime.now(ZoneId.of("UTC"));
    }
}
