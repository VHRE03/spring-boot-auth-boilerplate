package com.vhre.auth_boilerplate.modules.users.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Set;
import java.util.UUID;

@Schema(description = "Request for the creation of a new user")
public record UserRequestDTO(
        @NotBlank(message = "Username cannot be blank")
        @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
        @Schema(
                description = "Unique username for the user",
                example = "johndoe_99"
        )
        String username,

        @NotBlank(message = "Email cannot be blank")
        @Email(message = "Email must be a valid email address")
        @Size(max = 100, message = "Email cannot exceed 100 characters")
        @Schema(
                description = "Valid email address of the user",
                example = "john.doe@example.com"
        )
        String email,

        @NotBlank(message = "Password cannot be blank")
        @Size(min = 6, max = 255, message = "Password must be between 6 and 255 characters")
        @Schema(
                description = "Secure password for the user account",
                example = "P@ssw0rd123!"
        )
        String password,

        @NotBlank(message = "First name cannot be blank")
        @Size(max = 100, message = "First name cannot exceed 100 characters")
        @JsonProperty("first_name")
        @Schema(description = "User's first name", example = "John")
        String firstName,

        @NotBlank(message = "Last name cannot be blank")
        @Size(max = 100, message = "Last name cannot exceed 100 characters")
        @JsonProperty("last_name")
        @Schema(description = "User's last name", example = "Doe")
        String lastName,

        @NotEmpty(message = "At least one role ID must be provided")
        @JsonProperty("role_ids")
        @Schema(
                description = "Set of Role UUIDs to be assigned to the new user",
                example = "[\"1c7ff2c6-3ef1-4f59-b262-6b82f41fcdb5\"]"
        )
        Set<UUID> roleIds
) {
}
