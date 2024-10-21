package com.artsem.api.keycloakauthservice.controller;

import com.artsem.api.keycloakauthservice.service.RoleService;
import com.artsem.api.keycloakauthservice.service.UserService;
import com.artsem.api.keycloakauthservice.util.KeycloakRole;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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

    @Operation(summary = "Assign role to user", description = "Assign a specific role to a user by their ID.")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Role assigned successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @PutMapping("/{userId}")
    public ResponseEntity<?> assignRole(
            @PathVariable("userId") String userId,
            @RequestParam("role") KeycloakRole keycloakRole
    ) {
        roleService.assignRole(keycloakRole, userService.findUserById(userId));
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Delete role from user", description = "Remove a specific role from a user by their ID.")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Role deleted successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteRole(
            @PathVariable("userId") String userId,
            @RequestParam("role") KeycloakRole keycloakRole
    ) {
        roleService.deleteRoleFromUser(keycloakRole, userService.findUserById(userId));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
