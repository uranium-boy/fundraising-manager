package com.janissary.fundraising.dto.response;

import java.math.BigDecimal;

public record EventFinancialReportItem(String name, BigDecimal collectedAmount, String currencyCode) {
}
