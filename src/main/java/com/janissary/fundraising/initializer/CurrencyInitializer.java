package com.janissary.fundraising.initializer;

import com.janissary.fundraising.repository.CurrencyRepository;
import com.janissary.fundraising.model.Currency;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class CurrencyInitializer implements ApplicationRunner {
    // TODO: replace list with reading from file or sth
    private final CurrencyRepository currencyRepository;
    private final List<Currency> currencies = List.of(
            new Currency("EUR", new BigDecimal("1")),
            new Currency("PLN", new BigDecimal("4.27")),
            new Currency("USD", new BigDecimal("1.12")),
            new Currency("GBP", new BigDecimal("0.84")),
            new Currency("CHF", new BigDecimal("0.94"))
    );

    public CurrencyInitializer(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        if (currencyRepository.count() == 0) {
            currencyRepository.saveAll(currencies);
        }
    }
}
