package com.vhre.auth_boilerplate.modules.tokens.passwordReset.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Request object to trigger a password reset email")
public record PasswordResetRequestDTO(
        @NotBlank(message = "Email cannot be blank")
        @Email(message = "Must be a valid email format")
        @Schema(
                description = "The email address associated with the user account",
                example = "john.doe@example.com"
        )
        String email
) {
}
