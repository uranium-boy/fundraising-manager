package com.janissary.fundraising.service.impl;

import com.janissary.fundraising.model.Currency;
import com.janissary.fundraising.service.ExchangeService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class ExchangeServiceImpl implements ExchangeService {
    private static final int SCALE = 2;

    @Override
    public BigDecimal exchange(BigDecimal amount, Currency source, Currency target) {
        if (amount == null) {
            throw new IllegalArgumentException("Amount cannot be null");
        }
        if (source.equals(target) || amount.compareTo(BigDecimal.ZERO) <= 0) {
            return amount;
        }

        BigDecimal sourceRate = source.getExchangeRateToBase();
        BigDecimal targetRate = target.getExchangeRateToBase();

        if (sourceRate == null ||sourceRate.compareTo(BigDecimal.ZERO) == 0 ||
                targetRate == null || targetRate.compareTo(BigDecimal.ZERO) == 0) {
            throw new IllegalStateException("Invalid exchange rates");
        }

        return amount
                .multiply(target.getExchangeRateToBase())
                .divide(source.getExchangeRateToBase(), SCALE, RoundingMode.HALF_UP);
    }
}
