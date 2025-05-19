package com.janissary.fundraising.exception;

import lombok.Getter;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Getter
public class CollectionBoxNotAssignedException extends RuntimeException {
    private final ZonedDateTime timestamp;

    public CollectionBoxNotAssignedException(String id) {
        super(String.format("Collection Box with ID %s isn't assigned to any event.", id));
        this.timestamp = ZonedDateTime.now(ZoneId.of("UTC"));
    }
}
