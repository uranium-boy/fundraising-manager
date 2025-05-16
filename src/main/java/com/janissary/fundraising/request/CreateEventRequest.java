package com.janissary.fundraising.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateEventRequest {
    @NotNull
    @NotBlank
    private String name;

    @NotBlank
    @Size(min = 3, max = 3)
    private String defaultCurrencyCode;
}
