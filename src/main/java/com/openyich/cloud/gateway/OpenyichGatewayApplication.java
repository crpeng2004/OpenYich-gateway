package com.openyich.cloud.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;

@SpringBootApplication
@EnableWebFluxSecurity
public class OpenyichGatewayApplication {

  public static void main(String[] args) {
    SpringApplication.run(OpenyichGatewayApplication.class, args);
  }

  @Bean
  public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
    // @formatter:off
    return builder.routes().route("demo_path_route", r -> r.path("/get").uri("http://httpbin.org"))
        .build();
    // @formatter:on
  }

}
