package com.janissary.fundraising.service;

import com.janissary.fundraising.model.Currency;

import java.math.BigDecimal;

public interface ExchangeService {
    BigDecimal exchange(BigDecimal amount, Currency source, Currency target);
}
