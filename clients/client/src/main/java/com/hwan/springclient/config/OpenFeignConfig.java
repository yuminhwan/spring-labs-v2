package com.hwan.springclient.config;

import java.util.concurrent.TimeUnit;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Logger;
import feign.Retryer;
import feign.codec.ErrorDecoder;

@Configuration
@EnableFeignClients("com.hwan.springclient")
public class OpenFeignConfig {

    /**
     * 0.1초 간격으로 시작해 최대 3초의 간격으로 점점 증가하며, 최대 5번 재시도
     * IOException이 발생한 경우에만 적용된다.
     */
    @Bean
    Retryer.Default retryer() {
        return new Retryer.Default(
            100L,
            TimeUnit.SECONDS.toMillis(3L),
            5
        );
    }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    ErrorDecoder errorDecoder() {
        return new CustomErrorDecoder();
    }
}
