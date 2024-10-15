package com.artsem.api.keycloakauthservice.controller;

import com.artsem.api.keycloakauthservice.service.GroupService;
import com.artsem.api.keycloakauthservice.service.UserService;
import com.artsem.api.keycloakauthservice.util.KeycloakGroup;
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

    @PutMapping("/{userId}")
    public ResponseEntity<?> assignGroupToUser(
            @PathVariable("userId") String userId,
            @RequestParam("group") KeycloakGroup keycloakGroup
    ) {
        groupService.assignGroupToUser(keycloakGroup, userService.findUserById(userId));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteGroupFromUser(
            @PathVariable("userId") String userId,
            @RequestParam("group") KeycloakGroup keycloakGroup
    ) {
        groupService.deleteGroupFromUser(keycloakGroup, userService.findUserById(userId));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
