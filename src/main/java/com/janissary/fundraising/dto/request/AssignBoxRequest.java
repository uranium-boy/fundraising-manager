package com.janissary.fundraising.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record AssignBoxRequest (
    @NotNull
    @Positive
    Long eventId
) {}
