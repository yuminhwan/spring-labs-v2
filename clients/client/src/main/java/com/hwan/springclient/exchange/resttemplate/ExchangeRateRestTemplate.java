package com.hwan.springclient.exchange.resttemplate;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.hwan.springclient.config.ExchangeRateProperties;
import com.hwan.springclient.exchange.Currency;
import com.hwan.springclient.exchange.ExchangeRateResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ExchangeRateRestTemplate {

    private static final String API_KEY = "apikey";

    private final RestTemplate restTemplate;
    private final ExchangeRateProperties properties;

    public ExchangeRateResponse call(Currency source, Currency target) {
        return restTemplate.exchange(
            createApiUri(source, target),
            HttpMethod.GET,
            new HttpEntity<>(createHttpHeaders()),
            ExchangeRateResponse.class
        ).getBody();
    }

    private String createApiUri(Currency source, Currency target) {
        return UriComponentsBuilder.fromHttpUrl(properties.getUri())
            .queryParam("source", source.name())
            .queryParam("currencies", target.name())
            .encode()
            .toUriString();
    }

    private HttpHeaders createHttpHeaders() {
        final HttpHeaders headers = new HttpHeaders();
        headers.add(API_KEY, properties.getKey());
        return headers;
    }
}
