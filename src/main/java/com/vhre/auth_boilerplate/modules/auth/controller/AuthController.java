package com.vhre.auth_boilerplate.modules.auth.controller;

import com.vhre.auth_boilerplate.core.security.service.JwtService;
import com.vhre.auth_boilerplate.modules.auth.dto.LoginRequestDTO;
import com.vhre.auth_boilerplate.modules.auth.dto.TokenResponseDTO;
import com.vhre.auth_boilerplate.modules.auth.service.AuthService;
import com.vhre.auth_boilerplate.modules.tokens.passwordReset.dto.NewPasswordRequestDTO;
import com.vhre.auth_boilerplate.modules.tokens.passwordReset.dto.PasswordResetRequestDTO;
import com.vhre.auth_boilerplate.modules.tokens.passwordReset.service.PasswordResetTokenService;
import com.vhre.auth_boilerplate.modules.tokens.refresh.dto.RefreshTokenRequestDTO;
import com.vhre.auth_boilerplate.modules.tokens.refresh.entity.RefreshToken;
import com.vhre.auth_boilerplate.modules.tokens.refresh.service.RefreshTokenService;
import com.vhre.auth_boilerplate.modules.tokens.verification.dto.VerifyAccountRequestDTO;
import com.vhre.auth_boilerplate.modules.tokens.verification.service.VerificationTokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Endpoints for user authentication and token lifecycle management")
public class AuthController {

    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;
    private final JwtService jwtService;
    private final PasswordResetTokenService passwordResetTokenService;
    private final VerificationTokenService verificationTokenService;

    @PostMapping("/login")
    @Operation(
            summary = "Authenticate user credentials",
            description = "Validates the username and password against the database. If successful, issues a short-lived access token and a secure long-lived refresh token."
    )
    public ResponseEntity<TokenResponseDTO> login(@Valid @RequestBody LoginRequestDTO dto) {
        return ResponseEntity.ok(authService.login(dto));
    }

    @PostMapping("/refresh")
    @Operation(summary = "Refresh access token", description = "Provides a new JWT access token using a valid Refresh Token")
    public ResponseEntity<TokenResponseDTO> refreshToken(@Valid @RequestBody RefreshTokenRequestDTO requestDTO) {
        RefreshToken refreshToken = refreshTokenService.findByToken(requestDTO.refreshToken());
        refreshTokenService.verifyExpiration(refreshToken);

        String newAccessToken = jwtService.generateToken(refreshToken.getUser());
        return ResponseEntity.ok(new TokenResponseDTO(newAccessToken, refreshToken.getToken()));
    }

    @PostMapping("/password-reset/request")
    @Operation(summary = "Request password reset", description = "Generates a reset token and sends it to the user's email (Email sending pending)")
    public ResponseEntity<String> requestPasswordReset(@Valid @RequestBody PasswordResetRequestDTO request) {
        passwordResetTokenService.createPasswordResetToken(request);
        return ResponseEntity.ok("If the email exists, a reset link has been sent.");
    }

    @PostMapping("/password-reset/confirm")
    @Operation(summary = "Confirm new password", description = "Validates the reset token and updates the user's password")
    public ResponseEntity<String> confirmPasswordReset(@Valid @RequestBody NewPasswordRequestDTO request) {
        passwordResetTokenService.resetPassword(request);
        return ResponseEntity.ok("Password has been reset successfully.");
    }

    @PostMapping("/verify")
    @Operation(summary = "Verify user account", description = "Validates the verification token and enables the user account")
    public ResponseEntity<String> verifyAccount(@Valid @RequestBody VerifyAccountRequestDTO request) {
        verificationTokenService.verifyAccount(request);
        return ResponseEntity.ok("Account verified successfully. You can now log in.");
    }
}
