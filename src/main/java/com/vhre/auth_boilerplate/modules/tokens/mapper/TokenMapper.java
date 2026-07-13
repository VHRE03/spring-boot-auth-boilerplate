package com.vhre.auth_boilerplate.modules.tokens.mapper;

import com.vhre.auth_boilerplate.modules.tokens.passwordReset.dto.PasswordResetTokenResponseDTO;
import com.vhre.auth_boilerplate.modules.tokens.passwordReset.entity.PasswordResetToken;
import com.vhre.auth_boilerplate.modules.tokens.refresh.dto.RefreshTokenResponseDTO;
import com.vhre.auth_boilerplate.modules.tokens.refresh.entity.RefreshToken;
import com.vhre.auth_boilerplate.modules.tokens.verification.dto.VerificationTokenResponseDTO;
import com.vhre.auth_boilerplate.modules.tokens.verification.entity.VerificationToken;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TokenMapper {
    @Mapping(target = "userId", source = "user.id")
    RefreshTokenResponseDTO toRefreshResponse(RefreshToken token);

    @Mapping(target = "userId", source = "user.id")
    PasswordResetTokenResponseDTO toPasswordResetResponse(PasswordResetToken token);

    @Mapping(target = "userId", source = "user.id")
    VerificationTokenResponseDTO toVerificationResponse(VerificationToken token);
}
