package com.janissary.fundraising.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateEventRequest (
    @NotNull
    @NotBlank
    @Size(max = 255)
    String name,

    @NotBlank
    @Size(min = 3, max = 3)
    String defaultCurrencyCode
) {}
