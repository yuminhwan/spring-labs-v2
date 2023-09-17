package com.hwan.springclient.exchange.webclient;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import com.hwan.springclient.config.ExchangeRateProperties;
import com.hwan.springclient.exchange.Currency;
import com.hwan.springclient.exchange.ExchangeRateResponse;

@SpringBootTest
class ExchangeWebClient {

    private final WebClient webClient = WebClient.create("https://api.apilayer.com");

    @Autowired
    private ExchangeRateProperties properties;

    @Test
    void 현재_환율을_보여준다() {
        // given
        Currency target = Currency.USD;
        Currency source = Currency.KRW;

        // when
        ExchangeRateResponse response = webClient.get()
            .uri(uriBuilder -> uriBuilder.path("/currency_data/live")
                .queryParam("source", source.name())
                .queryParam("currencies", target.name())
                .build()
            )
            .header("apikey", properties.getKey())
            .retrieve()
            .bodyToMono(ExchangeRateResponse.class)
            .block();

        // then
        assertThat(response.source()).isEqualTo(source);
        assertThat(response.quotes()).isNotEmpty();
    }

    private String createApiUri(Currency source, Currency target) {
        return UriComponentsBuilder.fromHttpUrl(properties.getUri())
            .queryParam("source", source.name())
            .queryParam("currencies", target.name())
            .encode()
            .toUriString();
    }
}
