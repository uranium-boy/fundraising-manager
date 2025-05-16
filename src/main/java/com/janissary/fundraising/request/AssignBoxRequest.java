package com.janissary.fundraising.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class AssignBoxRequest {
    @NotNull
    @Positive
    private Long eventId;
}
