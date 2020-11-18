package com.sbr.rest.api.client;

import com.sbr.rest.api.filter.FeignClientInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "profile", url = "${profile.baseUrl}", configuration = {FeignClientInterceptor.class})
public interface ProfileClient {

    @GetMapping(value = "/{id}")
    String getProfileById(@PathVariable("id") String id);
}
