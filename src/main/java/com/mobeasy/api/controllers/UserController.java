package com.mobeasy.api.controllers;

import com.mobeasy.api.entities.dto.ExceptionDto;
import com.mobeasy.api.entities.dto.LoginDto;
import com.mobeasy.api.entities.dto.UserDto;
import com.mobeasy.api.services.UserService;
import com.mobeasy.api.services.security.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Validated
@Tag(name = "User", description = "Specific to users, manages the registration, login, update and deletion of users. The user must be authenticated to update or delete himself.")
public class UserController {

    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/register")
    @Operation(
            summary = "Register a new user",
            description = "There is no need to be authenticated to register a new user.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "User created"),
                    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ExceptionDto.class))),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema(implementation = ExceptionDto.class))),
                    @ApiResponse(responseCode = "409", description = "Conflict", content = @Content(schema = @Schema(implementation = ExceptionDto.class)))
            }
    )
    public ResponseEntity<UserDto> register(
            @Valid @RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.register(userDto), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    @Operation(
            summary = "Login a user",
            description = "There is no need to be authenticated to login a user. The token returned is used to authenticate the user in the future.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User logged in"),
                    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ExceptionDto.class))),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = ExceptionDto.class)))
            }
    )
    public ResponseEntity<String> login(
            @Valid @RequestBody LoginDto loginDto) {
        return new ResponseEntity<>(authService.login(loginDto), HttpStatus.OK);
    }

    @GetMapping("/me")
    @Operation(
            summary = "Get the authenticated user",
            description = "The user must be authenticated.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User found"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = ExceptionDto.class)))
            },
            security = @SecurityRequirement(name = "bearerAuth")
    )
    public ResponseEntity<UserDto> getMe(Authentication authentication) {
        return new ResponseEntity<>(userService.getMe(authentication), HttpStatus.OK);
    }

    @PutMapping("/me")
    @Operation(
            summary = "Update the authenticated user",
            description = "The user must be authenticated.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User updated"),
                    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ExceptionDto.class))),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = ExceptionDto.class))),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema(implementation = ExceptionDto.class))),
                    @ApiResponse(responseCode = "404", description = "User not found", content = @Content(schema = @Schema(implementation = ExceptionDto.class)))
            },
            security = @SecurityRequirement(name = "bearerAuth")
    )
    public ResponseEntity<UserDto> updateMe(@Valid @RequestBody UserDto userDto, Authentication authentication) {
        return new ResponseEntity<>(userService.updateMe(authentication, userDto), HttpStatus.OK);
    }

    @PatchMapping("/password")
    @Operation(
            summary = "Update the password of the authenticated user",
            description = "The user must be authenticated.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User updated"),
                    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ExceptionDto.class))),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = ExceptionDto.class))),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema(implementation = ExceptionDto.class))),
                    @ApiResponse(responseCode = "404", description = "User not found", content = @Content(schema = @Schema(implementation = ExceptionDto.class)))
            },
            security = @SecurityRequirement(name = "bearerAuth")
    )
    public ResponseEntity<UserDto> updateMyPassword(
            @Parameter(description = "Old password to be updated", required = true) @RequestParam String oldPassword,
            @Parameter(description = "New password to set", required = true) @RequestParam String newPassword,
            Authentication authentication) {
        return new ResponseEntity<>(userService.updatePassword(oldPassword, newPassword, authentication), HttpStatus.OK);
    }

    @DeleteMapping("/me")
    @Operation(
            summary = "Delete the authenticated user",
            description = "The user must be authenticated to delete himself.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "User deleted"),
                    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ExceptionDto.class))),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = ExceptionDto.class))),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema(implementation = ExceptionDto.class))),
                    @ApiResponse(responseCode = "404", description = "User not found", content = @Content(schema = @Schema(implementation = ExceptionDto.class)))
            },
            security = @SecurityRequirement(name = "bearerAuth")
    )
    public ResponseEntity<Void> deleteUser(Authentication authentication) {
        userService.deleteMe(authentication);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
