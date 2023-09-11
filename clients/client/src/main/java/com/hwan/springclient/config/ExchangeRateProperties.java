package com.hwan.springclient.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@ConfigurationProperties(prefix = "exchange.currency.api")
@RequiredArgsConstructor
public class ExchangeRateProperties {

    private final String uri;
    private final String key;

}
