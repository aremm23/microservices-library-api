package com.artsem.api.keycloakauthservice.service;

import com.artsem.api.keycloakauthservice.util.KeycloakGroup;
import org.keycloak.admin.client.resource.UserResource;

public interface GroupService {

    void assignGroupToUser(KeycloakGroup keycloakGroup, UserResource userResource);

    void deleteGroupFromUser(KeycloakGroup keycloakGroup, UserResource userResource);
}
