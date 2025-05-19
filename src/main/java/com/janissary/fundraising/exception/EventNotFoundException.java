package com.janissary.fundraising.exception;

import lombok.Getter;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Getter
public class EventNotFoundException extends RuntimeException {
    private final ZonedDateTime timestamp;

    public EventNotFoundException(String id) {
        super(String.format("Event with ID %s does not exist.", id));
        this.timestamp = ZonedDateTime.now(ZoneId.of("UTC"));
    }
}
