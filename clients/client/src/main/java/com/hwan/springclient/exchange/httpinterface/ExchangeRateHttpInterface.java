package com.hwan.springclient.exchange.httpinterface;

import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

import com.hwan.springclient.exchange.Currency;
import com.hwan.springclient.exchange.ExchangeRateResponse;

public interface ExchangeRateHttpInterface {

    @GetExchange
    ExchangeRateResponse call(
        @RequestHeader(name = "apiKey") String apiKey,
        @RequestParam(name = "source") Currency source,
        @RequestParam(name = "currencies") Currency currencies
    );

}
