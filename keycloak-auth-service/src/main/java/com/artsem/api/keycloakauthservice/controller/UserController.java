package com.artsem.api.keycloakauthservice.controller;

import com.artsem.api.keycloakauthservice.service.RoleService;
import com.artsem.api.keycloakauthservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.idm.RoleRepresentation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    public final UserService userService;

    public final RoleService roleService;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/{id}/roles")
    public ResponseEntity<List<RoleRepresentation>> getRoles(@PathVariable String id) {
        return ResponseEntity.ok(roleService.getAllUserRoles(userService.findUserById(id)));
    }

    @PatchMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestParam String username) {
        userService.forgotPassword(username);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}/verification-email")
    public ResponseEntity<?> sendVerifyEmail(@PathVariable String id) {
        userService.sendVerificationEmail(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
