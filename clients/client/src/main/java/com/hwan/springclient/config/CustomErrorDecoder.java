package com.hwan.springclient.config;

import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        return switch (response.status()) {
            case 400 -> new BadRequestException();
            case 500 -> new NotFoundException();
            default -> new Exception();
        };
    }

    public static class BadRequestException extends RuntimeException {
    }

    public static class NotFoundException extends RuntimeException {
    }
}
