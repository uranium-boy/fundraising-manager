package com.janissary.fundraising.exception;

public class EventNotFoundException extends ApiException {
    public EventNotFoundException(String id) {
        super(String.format("Event with ID %s does not exist.", id));
    }
}
