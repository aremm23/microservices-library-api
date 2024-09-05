package com.artsem.api.gatewayservice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class GatewayConfig {

    private final AuthenticationFilter authenticationFilter;

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("auth-service", r -> r.path("/api/v1/security/**")
                        .filters(f -> f.filter(authenticationFilter))
                        .uri("lb://auth-service"))
                .route("core-service", r -> r.path("/api/**")
                        .filters(f -> f.filter(authenticationFilter))
                        .uri("lb://core-service"))
                .route("library-service", r -> r.path("/api/**")
                        .filters(f -> f.filter(authenticationFilter))
                        .uri("lb://library-service"))
                .build();
    }
}
