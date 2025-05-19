package com.janissary.fundraising.dto.response;

import java.time.ZonedDateTime;

public record ErrorResponse(
        // yes, it is redundant, but idk what to put there
        int statusCode,
        String statusName,
        String message,
        String path,
        ZonedDateTime timestamp
) {
}
