package com.artsem.api.keycloakauthservice.model;

import jakarta.validation.constraints.NotBlank;

public record UserLoginRecord(

        @NotBlank(message = "Username is required")
        String username,

        @NotBlank(message = "Password is required")
        String password) {
}