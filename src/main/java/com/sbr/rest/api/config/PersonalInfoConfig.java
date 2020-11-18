package com.sbr.rest.api.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.sbr.rest.api.client")
public class PersonalInfoConfig {

}
