package com.sbr.rest.api.filter;

import feign.Logger;
import feign.RequestInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;

@Slf4j
public class FeignClientInterceptor {

    @Value("${profileSecurity.accessTokenUri}")
    private String accessTokenUri;
    @Value("${profileSecurity.clientId}")
    private String clientId;
    @Value("${profileSecurity.clientSecret}")
    private String clientSecret;
    @Value("${profileSecurity.grantType}")
    private String grantType;

    @Bean
    public RequestInterceptor oauth2schemeRequestInterceptor() {
        OAuth2FeignRequestInterceptor oAuth2FeignRequestInterceptor = new OAuth2FeignRequestInterceptor(new DefaultOAuth2ClientContext(), clientCredentialsResource());
        log.info("Oauth2 token: {}", oAuth2FeignRequestInterceptor.getToken());
        return oAuth2FeignRequestInterceptor;
    }

    private ClientCredentialsResourceDetails clientCredentialsResource() {
        ClientCredentialsResourceDetails clientCredentialsResourceDetails = new ClientCredentialsResourceDetails();
        clientCredentialsResourceDetails.setAccessTokenUri(accessTokenUri);
        clientCredentialsResourceDetails.setClientId(clientId);
        clientCredentialsResourceDetails.setClientSecret(clientSecret);
        clientCredentialsResourceDetails.setGrantType(grantType);
        clientCredentialsResourceDetails.setAuthenticationScheme(AuthenticationScheme.header);
        return clientCredentialsResourceDetails;
    }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.BASIC;
    }


}
