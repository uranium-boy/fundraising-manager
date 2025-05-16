package com.janissary.fundraising.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateEventRequest {
    @NotBlank
    private String name;

    @NotBlank
    @Size(min = 3, max = 3)
    private String defaultCurrencyCode;
}
