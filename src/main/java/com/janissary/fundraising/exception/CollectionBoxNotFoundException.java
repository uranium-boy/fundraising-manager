package com.janissary.fundraising.exception;

public class CollectionBoxNotFoundException extends ApiException {
    public CollectionBoxNotFoundException(String id) {
        super(String.format("Collection box with ID %s does not exist.", id));
    }
}
