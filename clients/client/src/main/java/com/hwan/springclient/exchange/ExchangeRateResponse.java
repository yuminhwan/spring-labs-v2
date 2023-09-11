package com.hwan.springclient.exchange;

import java.util.Map;

public record ExchangeRateResponse(
    long timestamp,
    Currency source,
    Map<String, Double> quotes
) {
}
