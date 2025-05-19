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
        // assumptions:
        // amount is validated on input and isn't negative or zero at this point
        // source and target currencies can be the same and checking logic is implemented here, not in controllers
        if (source.equals(target)) {
            return amount;
        }

        BigDecimal sourceRate = source.getExchangeRateToBase();
        BigDecimal targetRate = target.getExchangeRateToBase();

        if (sourceRate == null || targetRate == null || sourceRate.compareTo(BigDecimal.ZERO) == 0 || targetRate.compareTo(BigDecimal.ZERO) == 0) {
            throw new IllegalStateException("Invalid exchange rates");
        }

        return amount
                .multiply(target.getExchangeRateToBase())
                .divide(source.getExchangeRateToBase(), SCALE, RoundingMode.HALF_UP);
    }
}
