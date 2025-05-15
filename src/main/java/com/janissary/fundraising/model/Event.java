package com.janissary.fundraising.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne()
    @JoinColumn(name = "default_currency_id")
    private Currency defaultCurrency;

    @Column(precision = 19, scale = 6)
    private BigDecimal totalCollected;

    @OneToMany(mappedBy = "event")
    private Set<CollectionBox> boxes;
}
