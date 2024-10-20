package com.artsem.api.keycloakauthservice.controller;

import com.artsem.api.keycloakauthservice.service.GroupService;
import com.artsem.api.keycloakauthservice.service.UserService;
import com.artsem.api.keycloakauthservice.util.KeycloakGroup;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/groups")
public class GroupController {

    private final GroupService groupService;

    private final UserService userService;


    @Operation(summary = "Assign group to user", description = "Assign a specific group to a user by their ID.")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Group assigned to user"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @PutMapping("/{userId}")
    public ResponseEntity<?> assignGroupToUser(
            @PathVariable("userId") String userId,
            @RequestParam("group") KeycloakGroup keycloakGroup
    ) {
        groupService.assignGroupToUser(keycloakGroup, userService.findUserById(userId));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "Delete group from user", description = "Remove a specific group from a user by their ID.")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Group deleted from user"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteGroupFromUser(
            @PathVariable("userId") String userId,
            @RequestParam("group") KeycloakGroup keycloakGroup
    ) {
        groupService.deleteGroupFromUser(keycloakGroup, userService.findUserById(userId));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
