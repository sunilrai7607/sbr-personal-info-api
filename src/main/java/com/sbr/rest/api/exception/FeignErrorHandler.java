package com.sbr.rest.api.exception;

import feign.Response;
import feign.codec.ErrorDecoder;
import feign.template.UriUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import java.nio.charset.StandardCharsets;

@Configuration
public class FeignErrorHandler implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        if (HttpStatus.resolve(response.status()).is4xxClientError()) {
            return new RuntimeException(UriUtils.decode(response.request().url(), StandardCharsets.UTF_8));
        } else if (HttpStatus.resolve(response.status()).is5xxServerError()) {
            return new RuntimeException(UriUtils.decode(response.request().url(), StandardCharsets.UTF_8));
        } else if (HttpStatus.resolve(response.status()).is3xxRedirection()) {
            return new RuntimeException(UriUtils.decode(response.request().url(), StandardCharsets.UTF_8));
        }
        return new RuntimeException(UriUtils.decode(response.request().url(), StandardCharsets.UTF_8));
    }
}
