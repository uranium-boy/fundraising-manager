package com.janissary.fundraising.exception;

public class CollectionBoxNotAssignedException extends ApiException {
    public CollectionBoxNotAssignedException(String id) {
        super(String.format("Collection Box with ID %s isn't assigned to any event.", id));
    }
}
