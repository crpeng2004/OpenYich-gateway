package com.openyich.cloud.gateway;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "openyich.gateway")
public class OpenyichGatewayProperties {

  private final Security security = new Security();

  public Security getSecurity() {
    return security;
  }

  public static class Security {

    private final Jwt jwt = new Jwt();

    public Jwt getJwt() {
      return jwt;
    }

    public static class Jwt {

      private String secret;

      private String base64Secret;

      private long tokenValidityInSeconds = 1800; // 0.5 hour

      private long tokenValidityInSecondsForRememberMe = 2592000; // 30 days

      public String getSecret() {
        return secret;
      }

      public void setSecret(String secret) {
        this.secret = secret;
      }

      public String getBase64Secret() {
        return base64Secret;
      }

      public void setBase64Secret(String base64Secret) {
        this.base64Secret = base64Secret;
      }

      public long getTokenValidityInSeconds() {
        return tokenValidityInSeconds;
      }

      public void setTokenValidityInSeconds(long tokenValidityInSeconds) {
        this.tokenValidityInSeconds = tokenValidityInSeconds;
      }

      public long getTokenValidityInSecondsForRememberMe() {
        return tokenValidityInSecondsForRememberMe;
      }

      public void setTokenValidityInSecondsForRememberMe(long tokenValidityInSecondsForRememberMe) {
        this.tokenValidityInSecondsForRememberMe = tokenValidityInSecondsForRememberMe;
      }
    }
  }


}
