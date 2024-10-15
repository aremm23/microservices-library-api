package com.artsem.api.keycloakauthservice.controller;

import com.artsem.api.keycloakauthservice.model.UserRegisterRecord;
import com.artsem.api.keycloakauthservice.model.UserLoginRecord;
import com.artsem.api.keycloakauthservice.service.RoleService;
import com.artsem.api.keycloakauthservice.service.UserService;
import com.artsem.api.keycloakauthservice.util.KeycloakRole;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    public final UserService userService;

    public final RoleService roleService;

    @PostMapping("/login")
    public ResponseEntity<AccessTokenResponse> login(@RequestBody @Valid UserLoginRecord userLoginRecord) {
        return ResponseEntity.ok(userService.getJwt(userLoginRecord));
    }

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody @Valid UserRegisterRecord userRegisterRecord) {
        var createdUser = userService.createUser(userRegisterRecord);
        roleService.assignRole(KeycloakRole.USER, createdUser);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}

