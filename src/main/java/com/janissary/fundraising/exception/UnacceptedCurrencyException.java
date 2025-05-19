package com.janissary.fundraising.exception;

public class UnacceptedCurrencyException extends ApiException {
    public UnacceptedCurrencyException(String currencyCode) {
        super(String.format("%s is not an accepted currency.", currencyCode));
    }
}
