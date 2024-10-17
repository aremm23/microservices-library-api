package com.artsem.api.keycloakauthservice.service.impl;

import com.artsem.api.keycloakauthservice.model.UserRegisterRecord;
import com.artsem.api.keycloakauthservice.model.UserLoginRecord;
import com.artsem.api.keycloakauthservice.service.UserService;
import com.artsem.api.keycloakauthservice.util.StatusCodeValidator;
import jakarta.ws.rs.core.Response;
import lombok.Cleanup;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UsersResource usersResource;

    private final UserRepresentation userRepresentation;

    private final CredentialRepresentation credentialRepresentation;

    @Value("${app.keycloak.server-url}")
    private String serverUrl;

    @Value("${app.keycloak.user.client-id}")
    private String userClientId;

    @Value("${app.keycloak.realm}")
    private String realm;

    public AccessTokenResponse getJwt(UserLoginRecord userLoginRecord) {
        @Cleanup Keycloak userKeycloak = KeycloakBuilder.builder()
                .serverUrl(serverUrl)
                .realm(realm)
                .grantType(OAuth2Constants.PASSWORD)
                .clientId(userClientId)
                .username(userLoginRecord.username())
                .password(userLoginRecord.password())
                .build();

        return userKeycloak.tokenManager().getAccessToken();
    }

    @Override
    public UserResource createUser(UserRegisterRecord userRegisterRecord) {
        setUserRepresentation(userRegisterRecord);
        @Cleanup Response response = usersResource.create(userRepresentation);
        validateResponse(response);
        var createdUser = usersResource.search(userRegisterRecord.username()).get(0);
        sendVerificationEmail(createdUser.getId());
        return findUserById(createdUser.getId());
    }

    private void validateResponse(Response response) {
        log.info("Keycloak response status: {}", response.getStatus());
        StatusCodeValidator.validate(response);
    }

    @Override
    public void sendVerificationEmail(String userId) {
        log.info("Sending verification email...");
        //TODO setup email in keycloak realm settings
        //usersResource.get(userId).sendVerifyEmail();
    }

    @Override
    public void deleteUser(String userId) {
        @Cleanup Response response = usersResource.delete(userId);
        StatusCodeValidator.validate(response);
    }

    public void setUserRepresentation(UserRegisterRecord userRegisterRecord) {
        userRepresentation.setEnabled(true);
        userRepresentation.setUsername(userRegisterRecord.username());
        userRepresentation.setEmail(userRegisterRecord.email());
        userRepresentation.setFirstName(userRegisterRecord.username());
        userRepresentation.setLastName(userRegisterRecord.lastname());
        userRepresentation.setCreatedTimestamp(System.currentTimeMillis());

        // Set emailVerified to false only if the email settings in Keycloak are configured correctly.
        // If the settings are incorrect, leave the value as default or handle the error accordingly.
        userRepresentation.setEmailVerified(true);

        credentialRepresentation.setValue(userRegisterRecord.password());
        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);

        userRepresentation.setCredentials(List.of(credentialRepresentation));
    }

    @Override
    public UserResource findUserById(String userId) {
        return usersResource.get(userId);
    }

    @Override
    public void forgotPassword(String username) {
        UserResource userResource = usersResource.get(usersResource.search(username).get(0).getId());
        log.info("Sending password update email...");
        //TODO setup email in keycloak realm settings
        //userResource.executeActionsEmail(List.of(KeycloakEvent.UPDATE_PASSWORD.getEvent()));
    }

}
