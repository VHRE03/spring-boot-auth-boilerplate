package com.vhre.auth_boilerplate.modules.tokens.verification.dto;

import com.vhre.auth_boilerplate.modules.tokens.base.dto.BaseTokenResponseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Response object containing the details of an account verification token")
public class VerificationTokenResponseDTO extends BaseTokenResponseDTO {
}
