package com.hwan.springclient.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients("com.hwan.springclient")
public class OpenFeignConfig {
}
