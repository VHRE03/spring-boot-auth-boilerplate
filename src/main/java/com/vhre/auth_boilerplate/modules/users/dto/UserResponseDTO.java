package com.vhre.auth_boilerplate.modules.users.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vhre.auth_boilerplate.core.audit.dtos.BaseResponseDTO;
import com.vhre.auth_boilerplate.modules.roles.dto.RoleResponseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Response object representing a persisted user")
public class UserResponseDTO extends BaseResponseDTO {
    @Schema(description = "Unique identifier of the user", example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID id;

    @Schema(description = "Unique username of the user", example = "johndoe_99")
    private String username;

    @Schema(description = "Email address of the user", example = "john.doe@example.com")
    private String email;

    @JsonProperty("first_name")
    @Schema(description = "User's first name", example = "John")
    private String firstName;

    @JsonProperty("last_name")
    @Schema(description = "User's last name", example = "Doe")
    private String lastName;

    @JsonProperty("is_enabled")
    @Schema(description = "Indicates whether the user account is active", example = "true")
    private boolean isEnabled;

    @JsonProperty("is_account_non_locked")
    @Schema(description = "Indicates whether the user account is locked", example = "true")
    private boolean isAccountNonLocked;

    @Schema(description = "Set of security roles currently assigned to the user")
    private Set<RoleResponseDTO> roles;
}
