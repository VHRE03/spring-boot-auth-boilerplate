package com.vhre.auth_boilerplate.modules.tokens.refresh.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Request object for refreshing an expired access token")
public record RefreshTokenRequestDTO(
        @NotBlank(message = "Refresh token cannot be blank")
        @JsonProperty("refresh_token")
        @Schema(
                description = "The valid refresh token previously issued to the client",
                example = "d2b290b2-3c22-4809-8422-92131920ac34"
        )
        String refreshToken
) {
}
