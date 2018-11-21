package com.openyich.cloud.gateway;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "openyich.gateway")
public class OpenyichGatewayProperties {

}
