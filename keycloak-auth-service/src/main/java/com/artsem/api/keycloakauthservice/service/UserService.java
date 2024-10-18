package com.artsem.api.keycloakauthservice.service;

import com.artsem.api.keycloakauthservice.model.UserRegisterRecord;
import com.artsem.api.keycloakauthservice.model.UserLoginRecord;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.AccessTokenResponse;

public interface UserService {

    UserResource createUser(UserRegisterRecord userRegisterRecord);

    AccessTokenResponse getJwt(UserLoginRecord userLoginRecord);

    void sendVerificationEmail(String userId);

    UserResource findUserById(String userId);

    void forgotPassword(String username);

    void deleteUser(String userId);
}
