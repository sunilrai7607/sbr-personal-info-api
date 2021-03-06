## sbr-personal-info-api 

Personal Info is a microservice which pull information from the profile microservice.
Profile microservice which is Oauth2 enabled. 

FeignClient to consume the Profile service. 
```java
@FeignClient(name = "profile", url = "${profile.baseUrl}", configuration = {FeignClientInterceptor.class})
public interface ProfileClient {

    @GetMapping(value = "/{id}")
    String getProfileById(@PathVariable("id") String id);
}

```

FeignClientInterceptor generate Bearer Tokens when personal info micro service is up and send bearer token in each request 
```java
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
```

Add the configuration in application.yml

```yaml
profileSecurity:
  accessTokenUri: http://<HOST:PORT>/oauth/token
  clientId: <clientId> # add your clientID
  clientSecret: <client-secret> # add your client Secrete 
  grantType: client_credentials

feign:
  oauth2:
    enabled: true
  hystrix:
    enabled: false
```