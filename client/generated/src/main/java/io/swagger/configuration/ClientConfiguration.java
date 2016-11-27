package io.swagger.configuration;

import feign.Logger;
import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.resource.BaseOAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.client.token.grant.implicit.ImplicitResourceDetails;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

@Configuration
@EnableConfigurationProperties
public class ClientConfiguration {

  @Bean
  @ConditionalOnProperty("swaggerPetstore.security.petstoreApplication.client-id")
  public OAuth2FeignRequestInterceptor petstoreApplicationRequestInterceptor() {
    return new OAuth2FeignRequestInterceptor(new DefaultOAuth2ClientContext(), petstoreApplicationResourceDetails());
  }

  @Bean
  @ConditionalOnProperty("swaggerPetstore.security.petstoreApplication.client-id")
  @ConfigurationProperties("swaggerPetstore.security.petstoreApplication")
  public ClientCredentialsResourceDetails petstoreApplicationResourceDetails() {
    ClientCredentialsResourceDetails details = new ClientCredentialsResourceDetails();
    details.setAccessTokenUri("");
    return details;
  }

  @Value("${ swaggerPetstore.security.petstoreApiKey.key:}")
  private String petstoreApiKeyKey;

  @Bean
  @ConditionalOnProperty(name = "swaggerPetstore.security.petstoreApiKey.key")
  public ApiKeyRequestInterceptor petstoreApiKeyRequestInterceptor() {
    return new ApiKeyRequestInterceptor("header", "X-APIKEY", this.petstoreApiKeyKey);
  }

  @Bean
  @ConditionalOnProperty("swaggerPetstore.security.petstorePassword.client-id")
  public OAuth2FeignRequestInterceptor petstorePasswordRequestInterceptor() {
    return new OAuth2FeignRequestInterceptor(new DefaultOAuth2ClientContext(), petstorePasswordResourceDetails());
  }

  @Bean
  @ConditionalOnProperty("swaggerPetstore.security.petstorePassword.client-id")
  @ConfigurationProperties("swaggerPetstore.security.petstorePassword")
  public ResourceOwnerPasswordResourceDetails petstorePasswordResourceDetails() {
    ResourceOwnerPasswordResourceDetails details = new ResourceOwnerPasswordResourceDetails();
    details.setAccessTokenUri("");
    return details;
  }

}