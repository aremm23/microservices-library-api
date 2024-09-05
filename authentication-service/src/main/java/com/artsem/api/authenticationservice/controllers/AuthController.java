package com.artsem.api.authenticationservice.controllers;

import com.artsem.api.authenticationservice.model.dto.LoginRequestDto;
import com.artsem.api.authenticationservice.model.dto.LoginResponseDto;
import com.artsem.api.authenticationservice.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/security")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "/auth")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto dto) {
        return ResponseEntity.ok(authService.authenticate(dto));
    }
}
