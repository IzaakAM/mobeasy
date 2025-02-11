package com.mobeasy.api.entities.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class LoginDto {
    @Schema(description = "User's email", requiredMode = Schema.RequiredMode.REQUIRED)
    private @Email String email;

    @Schema(description = "User's password", requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;
}
