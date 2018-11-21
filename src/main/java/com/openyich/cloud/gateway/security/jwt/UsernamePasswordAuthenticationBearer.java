package com.openyich.cloud.gateway.security.jwt;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.nimbusds.jwt.SignedJWT;

import reactor.core.publisher.Mono;

/**
 * This converter takes a SignedJWT and extracts all information contained to build an
 * Authentication Object The signed JWT has already been verified.
 */
public class UsernamePasswordAuthenticationBearer {

  public static Mono<Authentication> create(SignedJWT signedJWTMono) {
    SignedJWT signedJWT = signedJWTMono;
    String subject;
    String auths;

    try {
      subject = signedJWT.getJWTClaimsSet().getSubject();
      auths = (String) signedJWT.getJWTClaimsSet().getClaim("roles");
    } catch (ParseException e) {
      return Mono.empty();
    }
    List<GrantedAuthority> authorities = Stream.of(auths.split(","))
        .map(a -> new SimpleGrantedAuthority(a)).collect(Collectors.toList());

    return Mono.justOrEmpty(new UsernamePasswordAuthenticationToken(subject, null, authorities));

  }
}
