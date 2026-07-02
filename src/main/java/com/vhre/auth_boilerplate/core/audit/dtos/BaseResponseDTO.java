package com.vhre.auth_boilerplate.core.audit.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public abstract class BaseResponseDTO {
    @Schema(description = "Unique record identifier", example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID id;

    @Schema(description = "Exact date the record was created")
    private LocalDateTime created_at;

    @Schema(description = "Date of the last modification to the record")
    private LocalDateTime updated_at;
}
