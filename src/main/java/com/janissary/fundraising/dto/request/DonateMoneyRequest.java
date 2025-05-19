package com.janissary.fundraising.dto.request;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record DonateMoneyRequest (
    @NotBlank
    @Size(min = 3, max = 3)
    String currency,

    @NotNull
    @Positive
    @DecimalMin(value = "0.01")
    @Digits(integer = 17, fraction = 2)
    BigDecimal amount
) {}
