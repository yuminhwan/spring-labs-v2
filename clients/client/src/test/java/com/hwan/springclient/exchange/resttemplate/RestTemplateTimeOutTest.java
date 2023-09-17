package com.hwan.springclient.exchange.resttemplate;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.ResourceAccessException;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.http.Body;
import com.hwan.springclient.exchange.Currency;
import com.hwan.springclient.util.mock.WireMockServerConfig;

@SpringBootTest(
    classes = {WireMockServerConfig.class},
    properties = {"exchange.currency.api.uri=http://localhost:8090/currency_data/live"}
)
class RestTemplateTimeOutTest {

    @Autowired
    private WireMockServer mockServer;

    @Autowired
    private ExchangeRateRestTemplate exchangeRateRestTemplate;

    @Test
    void restTemplate_타임아웃() {
        mockServer.stubFor(
            get(urlPathEqualTo("/currency_data/live"))
                .willReturn(aResponse()
                    .withStatus(200)
                    .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .withResponseBody(new Body(
                            """
                                {
                                  "success": true,
                                  "timestamp": 1694925663,
                                  "source": "KRW",
                                  "quotes": {
                                    "KRWUSD": 0.000752
                                  }
                                }
                                """
                        )
                    )
                    .withFixedDelay(10000)
                ));

        // given
        Currency target = Currency.USD;
        Currency source = Currency.KRW;

        // when & then
        assertThatThrownBy(() -> {
            exchangeRateRestTemplate.call(source, target);
        })
            .isInstanceOf(ResourceAccessException.class);
    }

}
