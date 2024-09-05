package com.artsem.api.authenticationservice.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponseDto {
    private String token;
    private Long id;
}

