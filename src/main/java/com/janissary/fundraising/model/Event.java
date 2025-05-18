package com.janissary.fundraising.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "events")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne()
    @JoinColumn(name = "default_currency_id", nullable = false)
    private Currency defaultCurrency;

    @Column(precision = 19, scale = 2, nullable = false)
    @Builder.Default
    private BigDecimal totalCollected = BigDecimal.ZERO;

    @OneToMany(mappedBy = "event")
    private Set<CollectionBox> boxes;
}
