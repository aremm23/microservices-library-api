package com.artsem.api.coreservice.config;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new Components()
                        .addSecuritySchemes("bearerAuth",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                                        .in(SecurityScheme.In.HEADER)
                                        .name("Authorization")))
                .info(new Info()
                        .title("Booking Service API")
                        .description("This API allows for managing bookings, including operations for creating, updating, retrieving, and deleting books. Additionally, it facilitates user management and role assignment. All actions involving books will trigger messages to the library service via RabbitMQ for further processing.")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Artsem Maiseyenka")
                                .url("https://github.com/aremm23")
                        )
                );
    }
}