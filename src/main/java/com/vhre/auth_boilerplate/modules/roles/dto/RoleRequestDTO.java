package com.vhre.auth_boilerplate.modules.roles.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Schema(description = "Request for the creation of a new role")
public record RoleRequestDTO(
        @NotBlank(message = "The role name cannot be empty")
        @Pattern(
                regexp = "^ROLE_[A-Z_]+$",
                message = "The role must begin with ‘ROLE_’ and contain only letters and underscores. (Note: The system will automatically convert your entry to uppercase.)"
        )
        @Schema(
                description = "Full name of the security role",
                example = "ROLE_USER"
        )
        String name
) {
}
