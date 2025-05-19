package com.janissary.fundraising.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "collection_boxes")
@Getter
@Setter
public class CollectionBox {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @ElementCollection
    @CollectionTable(
            name = "box_currency_amounts",
            joinColumns = @JoinColumn(name = "collection_box_id")
    )
    @MapKeyJoinColumn(name = "currency_id")
    @Column(name = "amount", precision = 19, scale = 2)
    private Map<Currency, BigDecimal> collectedAmounts = new HashMap<>();
}
