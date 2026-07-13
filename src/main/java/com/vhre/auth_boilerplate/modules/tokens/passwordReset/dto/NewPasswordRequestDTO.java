package com.vhre.auth_boilerplate.modules.tokens.passwordReset.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Request object to set a new password using a valid reset token")
public record NewPasswordRequestDTO(
        @NotBlank(message = "Token cannot be blank")
        @Schema(
                description = "The security token sent to the user's email",
                example = "550e8400-e29b-41d4-a716-446655440000"
        )
        String token,

        @NotBlank(message = "New password cannot be blank")
        @Size(min = 6, max = 255, message = "Password must be between 6 and 255 characters")
        @JsonProperty("new_password")
        @Schema(
                description = "The new secure password chosen by the user",
                example = "N3wP@ssw0rd!"
        )
        String newPassword
) {
}
