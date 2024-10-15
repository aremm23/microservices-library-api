package com.artsem.api.keycloakauthservice.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum KeycloakRole {
    ADMIN("ADMIN"),
    USER("USER");
    private final String role;
}
