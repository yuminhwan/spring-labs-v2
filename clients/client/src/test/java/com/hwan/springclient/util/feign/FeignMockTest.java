package com.hwan.springclient.util.feign;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.boot.test.context.SpringBootTest;

import com.hwan.springclient.util.mock.WireMockServerConfig;

@SpringBootTest(
    classes = {FeignTestContext.class, WireMockServerConfig.class},
    properties = {
        "exchange.currency.api.uri=localhost:8090/currency_data/live"
    }
)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface FeignMockTest {
}
