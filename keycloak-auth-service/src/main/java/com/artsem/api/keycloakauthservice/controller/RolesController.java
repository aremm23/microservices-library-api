package com.artsem.api.keycloakauthservice.controller;

import com.artsem.api.keycloakauthservice.service.RoleService;
import com.artsem.api.keycloakauthservice.service.UserService;
import com.artsem.api.keycloakauthservice.util.KeycloakRole;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/roles")
@RequiredArgsConstructor
public class RolesController {

    private final UserService userService;

    private final RoleService roleService;

    @PutMapping("/{userId}")
    public ResponseEntity<?> assignRole(
            @PathVariable("userId") String userId,
            @RequestParam("role") KeycloakRole keycloakRole
    ) {
        roleService.assignRole(keycloakRole, userService.findUserById(userId));
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteRole(
            @PathVariable("userId") String userId,
            @RequestParam("role") KeycloakRole keycloakRole
    ) {
        roleService.deleteRoleFromUser(keycloakRole, userService.findUserById(userId));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
