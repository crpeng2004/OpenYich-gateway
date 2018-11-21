package com.openyich.cloud.gateway.security.bearer;

import java.util.function.Function;
import java.util.function.Predicate;

import org.springframework.security.core.Authentication;
import org.springframework.web.server.ServerWebExchange;

import com.openyich.cloud.gateway.security.jwt.AuthorizationHeaderPayload;
import com.openyich.cloud.gateway.security.jwt.JWTCustomVerifier;
import com.openyich.cloud.gateway.security.jwt.UsernamePasswordAuthenticationBearer;

import reactor.core.publisher.Mono;

/**
 * This converter extracts a bearer token from a WebExchange and returns an Authentication object if
 * the JWT token is valid. Validity means is well formed and signature is correct
 */
public class ServerHttpBearerAuthenticationConverter
    implements
      Function<ServerWebExchange, Mono<Authentication>> {

  private static final String BEARER = "Bearer ";
  private static final Predicate<String> matchBearerLength =
      authValue -> authValue.length() > BEARER.length();
  private static final Function<String, Mono<String>> isolateBearerValue =
      authValue -> Mono.justOrEmpty(authValue.substring(BEARER.length()));

  private JWTCustomVerifier jwtVerifier = new JWTCustomVerifier();

  /**
   * Apply this function to the current WebExchange, an Authentication object is returned when
   * completed.
   *
   * @param serverWebExchange
   * @return
   */
  @Override
  public Mono<Authentication> apply(ServerWebExchange serverWebExchange) {
    return Mono.justOrEmpty(serverWebExchange).flatMap(AuthorizationHeaderPayload::extract)
        .filter(matchBearerLength).flatMap(isolateBearerValue).flatMap(jwtVerifier::check)
        .flatMap(UsernamePasswordAuthenticationBearer::create).log();
  }
}
