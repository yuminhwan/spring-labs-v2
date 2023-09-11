package com.hwan.springclient.exchange.resttemplate;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hwan.springclient.exchange.Currency;
import com.hwan.springclient.exchange.ExchangeRateResponse;

@SpringBootTest
class ExchangeRateRestTemplateTest {

    @Autowired
    private ExchangeRateRestTemplate exchangeRateRestTemplate;

    @Test
    void 현재_환율을_보여준다() {
        // given
        Currency target = Currency.USD;
        Currency source = Currency.KRW;

        // when
        ExchangeRateResponse response = exchangeRateRestTemplate.call(source, target);

        // then
        assertThat(response.source()).isEqualTo(source);
        assertThat(response.quotes()).isNotEmpty();
    }
}
