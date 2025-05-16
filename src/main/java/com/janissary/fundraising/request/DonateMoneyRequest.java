package com.janissary.fundraising.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public class DonateMoneyRequest {
    @NotBlank
    @Size(min = 3, max = 3)
    private String currency;

    @Positive
    private BigDecimal amount;
}
