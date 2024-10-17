package com.artsem.api.keycloakauthservice.service.impl;

import com.artsem.api.keycloakauthservice.service.GroupService;
import com.artsem.api.keycloakauthservice.util.KeycloakGroup;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.resource.GroupsResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.GroupRepresentation;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupsResource groupsResource;

    @Override
    public void assignGroupToUser(KeycloakGroup keycloakGroup, UserResource userResource) {
        GroupRepresentation groupRepresentation = groupsResource.groups().stream()
                .filter(groupRepresentation1 -> groupRepresentation1.getName().equals(keycloakGroup.getGroup()))
                .findFirst().orElseThrow();
        userResource.joinGroup(groupRepresentation.getId());
    }

    @Override
    public void deleteGroupFromUser(KeycloakGroup keycloakGroup, UserResource userResource) {
        userResource.leaveGroup(groupsResource.group(keycloakGroup.getGroup()).toRepresentation().getId());
    }
}
