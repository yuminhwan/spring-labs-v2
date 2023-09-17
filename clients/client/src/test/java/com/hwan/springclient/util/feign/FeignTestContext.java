package com.hwan.springclient.util.feign;

import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;

import com.hwan.springclient.config.CustomPropertiesConfig;
import com.hwan.springclient.config.OpenFeignConfig;

@ImportAutoConfiguration({
    OpenFeignConfig.class,
    CustomPropertiesConfig.class,
    FeignAutoConfiguration.class,
    HttpMessageConvertersAutoConfiguration.class
})
class FeignTestContext {
}
