package com.hwan.springclient.exchange.openfeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.hwan.springclient.exchange.Currency;
import com.hwan.springclient.exchange.ExchangeRateResponse;

@FeignClient(name = "ExchangeRateOpenFeign", url = "${exchange.currency.api.uri}")
public interface ExchangeRateOpenFeign {

    @GetMapping
    ExchangeRateResponse call(
        @RequestHeader(name = "apiKey") String apiKey,
        @RequestParam(name = "source") Currency source,
        @RequestParam(name = "currencies") Currency currencies
    );

}
