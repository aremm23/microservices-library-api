package com.artsem.api.gatewayservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fallback")
public class FallbackController {

    @GetMapping("/core-service")
    public ResponseEntity<String> coreServiceFallback() {
        return new ResponseEntity<>(
                "Core service down",
                HttpStatus.GATEWAY_TIMEOUT
        );
    }

    @GetMapping("/auth-service")
    public ResponseEntity<String> authServiceFallback() {
        return new ResponseEntity<>(
                "Auth service down",
                HttpStatus.GATEWAY_TIMEOUT
        );
    }

    @GetMapping("/library-service")
    public ResponseEntity<String> libraryServiceFallback() {
        return new ResponseEntity<>(
                "Library service down",
                HttpStatus.GATEWAY_TIMEOUT
        );
    }

}