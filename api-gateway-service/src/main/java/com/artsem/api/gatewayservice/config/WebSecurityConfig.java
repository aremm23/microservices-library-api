package com.artsem.api.gatewayservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
@Configuration
public class WebSecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http.authorizeExchange(authorizeExchangeSpec -> authorizeExchangeSpec
                .pathMatchers("/api/v1/auth/login").permitAll()
                .pathMatchers("/api/v1/auth/register").permitAll()
                .pathMatchers("/actuator/health").permitAll()
                .pathMatchers("/core-service/actuator/health").permitAll()
                .pathMatchers("/library-service/actuator/health").permitAll()
                .pathMatchers("/auth-service/actuator/health").permitAll()
                .pathMatchers("/core-service/service-health/**").permitAll()
                .pathMatchers("/fallback/**").permitAll()
                .anyExchange().authenticated()
        );
        http.oauth2ResourceServer(oAuth2ResourceServerSpec -> oAuth2ResourceServerSpec
                .jwt(Customizer.withDefaults()));
        http.csrf(ServerHttpSecurity.CsrfSpec::disable);
        return http.build();
    }
}
