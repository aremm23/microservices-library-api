package com.artsem.api.crudservice;

import com.artsem.api.crudservice.model.UserDetails;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.time.LocalDateTime;

/**
 * DTO for {@link UserDetails}
 */
@Value
public class UserDto {
    @NotNull(message = "is null")
    @Email(message = "not email")
    String email;
    @NotNull(message = "is null")
    LocalDateTime createdAt;
    @NotNull(message = "is null")
    @Min(message = "less then 18", value = 18)
    @Max(message = "more than 150", value = 150)
    Integer age;
}