package com.artsem.api.gatewayservice.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fallback")
public class FallbackController {

    @GetMapping("/core-service")
    public String coreServiceFallback() {
        return "Core service down";
    }

    @GetMapping("/test")
    private String test() {
        return "Hallo Test";
    }
}