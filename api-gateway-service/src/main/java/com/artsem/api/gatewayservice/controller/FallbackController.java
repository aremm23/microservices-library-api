package com.artsem.api.gatewayservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fallback")
public class FallbackController {

    @RequestMapping(value = "/core-service",
            method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.PATCH, RequestMethod.DELETE})
    public ResponseEntity<String> coreServiceFallback() {
        return new ResponseEntity<>(
                "Core service down",
                HttpStatus.GATEWAY_TIMEOUT
        );
    }

    @RequestMapping(value = "/auth-service",
            method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.PATCH, RequestMethod.DELETE})
    public ResponseEntity<String> authServiceFallback() {
        return new ResponseEntity<>(
                "Auth service down",
                HttpStatus.GATEWAY_TIMEOUT
        );
    }

    @RequestMapping(value = "/library-service",
            method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.PATCH, RequestMethod.DELETE})
    public ResponseEntity<String> libraryServiceFallback() {
        return new ResponseEntity<>(
                "Library service down",
                HttpStatus.GATEWAY_TIMEOUT
        );
    }

}