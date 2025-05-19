package com.janissary.fundraising.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "currencies")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Currency {
    // TODO: maybe use JSR-354?
    @Id
    @Column(length = 3)
    private String id;  // ISO codes

    @Column(precision = 19, scale = 6)
    private BigDecimal exchangeRateToBase;
}
