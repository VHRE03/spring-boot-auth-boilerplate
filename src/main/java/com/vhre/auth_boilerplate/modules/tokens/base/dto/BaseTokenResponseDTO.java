package com.vhre.auth_boilerplate.modules.tokens.base.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vhre.auth_boilerplate.core.audit.dtos.BaseResponseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class BaseTokenResponseDTO extends BaseResponseDTO {
    @Schema(description = "The generated security token", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    private String token;

    @JsonProperty("expiry_date")
    @Schema(description = "The exact date and time when this token expires")
    private LocalDateTime expiryDate;

    @JsonProperty("user_id")
    @Schema(description = "Unique identifier of the user who owns this token", example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID userId;
}
