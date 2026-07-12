package com.vhre.auth_boilerplate.modules.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Authentication response containing security tokens for subsequent API access")
public record TokenResponseDTO(
        @JsonProperty("access_token")
        @Schema(
                description = "Short-lived JWT access token used to authenticate subsequent API requests",
                example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJqb2huZG9lXzk5IiwiZXhwIjoxNzE5OTk5OTk5fQ..."
        )
        String accessToken,

        @JsonProperty("refresh_token")
        @Schema(
                description = "Long-lived secure token used to generate new access tokens without full re-authentication",
                example = "d2b290b2-3c22-4809-8422-92131920ac34"
        )
        String refreshToken
) {
}
