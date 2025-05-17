package com.janissary.fundraising.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class DonateMoneyRequest {
    @NotBlank
    @Size(min = 3, max = 3)
    private String currency;

    @NotNull
    @Positive
    @DecimalMin(value = "0.01")
    private BigDecimal amount;
}
