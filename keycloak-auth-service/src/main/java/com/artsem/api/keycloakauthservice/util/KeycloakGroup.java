package com.artsem.api.keycloakauthservice.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum KeycloakGroup {
    CLIENT("CLIENT"),
    SUPERVISOR("SUPERVISOR");
    private final String group;
}
