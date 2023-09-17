package com.hwan.springclient.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import com.hwan.springclient.exchange.httpinterface.ExchangeRateHttpInterface;

@Configuration
public class HttpInterfaceConfig {

    @Bean
    public ExchangeRateHttpInterface exchangeRateHttpInterface(ExchangeRateProperties properties) {
        WebClient client = WebClient.builder().baseUrl(properties.getUri()).build();
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(client)).build();

        return factory.createClient(ExchangeRateHttpInterface.class);
    }

}
