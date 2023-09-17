package com.hwan.springclient.exchange.openfeign;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.hwan.springclient.config.CustomErrorDecoder;
import com.hwan.springclient.config.ExchangeRateProperties;
import com.hwan.springclient.exchange.Currency;
import com.hwan.springclient.util.feign.FeignMockTest;

@FeignMockTest
class OpenFeignTest {

    @Autowired
    private ExchangeRateOpenFeign exchangeRateOpenFeign;

    @Autowired
    private ExchangeRateProperties properties;

    @Autowired
    private WireMockServer mockServer;

    @Test
    void errorDecoder() {
        // given
        Currency target = Currency.USD;
        Currency source = Currency.KRW;

        mockServer.stubFor(
            get(urlPathEqualTo("/currency_data/live"))
                .willReturn(aResponse()
                    .withStatus(400)
                ));

        // when & then
        assertThatThrownBy(() -> exchangeRateOpenFeign.call(properties.getKey(), source, target))
            .isInstanceOf(CustomErrorDecoder.BadRequestException.class);
    }

}
