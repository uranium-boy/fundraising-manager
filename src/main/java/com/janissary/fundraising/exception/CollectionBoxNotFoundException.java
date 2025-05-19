package com.janissary.fundraising.exception;

import lombok.Getter;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Getter
public class CollectionBoxNotFoundException extends RuntimeException {
    private final ZonedDateTime timestamp;

    public CollectionBoxNotFoundException(String id) {
        super(String.format("Collection box with ID %s does not exist.", id));
        this.timestamp = ZonedDateTime.now(ZoneId.of("UTC"));
    }
}
