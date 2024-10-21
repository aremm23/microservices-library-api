package com.artsem.api.coreservice.config;

import com.artsem.api.coreservice.util.JwtAuthenticationConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final JwtAuthenticationConverter jwtAuthenticationConverter;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
                        .requestMatchers("/core-service-docs/**").permitAll()
                        .requestMatchers("/swagger-ui/**").permitAll()
                        .requestMatchers("/v3/api-docs/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/book").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/book/*").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/book/*").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/v1/book/*").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.GET, "/api/v1/book").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .requestMatchers("/actuator/health").permitAll()
                        .requestMatchers("/service-health/**").permitAll()
                        .anyRequest().authenticated())
                .oauth2ResourceServer(oath2 -> oath2
                        .jwt(jwt -> jwt
                                .jwtAuthenticationConverter(jwtAuthenticationConverter)
                        )
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(AbstractHttpConfigurer::disable)
                .build();
    }
}
