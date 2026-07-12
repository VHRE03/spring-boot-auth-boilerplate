package com.vhre.auth_boilerplate.modules.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Credentials required to authenticate a user and issue security tokens")
public record LoginRequestDTO(
        @NotBlank(message = "Username cannot be blank")
        @Schema(
                description = "The unique username of the user trying to log in",
                example = "johndoe_99",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        String username,

        @NotBlank(message = "Password cannot be blank")
        @Schema(
                description = "The secret password associated with the account",
                example = "P@ssw0rd123!",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        String password
) {
}
