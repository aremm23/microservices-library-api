package com.artsem.api.coreservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service-health/")
public class HealthCheckController {

    @Value("${server.port}")
    private int port;

    @GetMapping("/check-port")
    public String checkPort() {
        return "Service is running on port: " + port;
    }
}
