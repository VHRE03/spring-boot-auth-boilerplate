package com.vhre.auth_boilerplate.modules.roles.dto;

import com.vhre.auth_boilerplate.core.audit.dtos.BaseResponseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Response object representing a persisted role")
public class RoleResponseDTO extends BaseResponseDTO {

    @Schema(description = "Full name of the security role", example = "ROLE_USER")
    private String name;
}
