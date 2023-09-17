package com.hwan.springclient.util.mock;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import com.github.tomakehurst.wiremock.WireMockServer;

@TestConfiguration
public class WireMockServerConfig {

    @Bean(initMethod = "start", destroyMethod = "stop")
    WireMockServer mockServer() {
        return new WireMockServer(8090);
    }
}
