package com.mobeasy.api.entities.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Schema(description = "User DTO")
@RequiredArgsConstructor
public class UserDto {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "User firstname and lastname")
    private String name; // TODO: change it, name + username seems to be strange.

    @Schema(description = "Username or pseudo")
    private String username;

    @Schema(description = "User's email", requiredMode = Schema.RequiredMode.REQUIRED)
    private @Email String email;

    @Schema(description = "User's password", requiredMode = Schema.RequiredMode.REQUIRED, accessMode = Schema.AccessMode.WRITE_ONLY)
    private String password;

    @Schema(description = "User is it an administrator?")
    private boolean isAdmin;
}
