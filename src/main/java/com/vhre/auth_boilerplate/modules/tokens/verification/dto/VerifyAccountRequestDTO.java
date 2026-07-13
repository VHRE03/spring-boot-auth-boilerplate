package com.vhre.auth_boilerplate.modules.tokens.verification.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Request object to verify a newly registered account")
public record VerifyAccountRequestDTO(
        @NotBlank(message = "Verification token cannot be blank")
        @Schema(
                description = "The verification token sent to the user's email during registration",
                example = "1c7ff2c6-3ef1-4f59-b262-6b82f41fcdb5"
        )
        String token
) {
}
