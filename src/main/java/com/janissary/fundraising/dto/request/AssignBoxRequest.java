package com.janissary.fundraising.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class AssignBoxRequest {
    @NotNull
    @Positive
    private Long eventId;
}
