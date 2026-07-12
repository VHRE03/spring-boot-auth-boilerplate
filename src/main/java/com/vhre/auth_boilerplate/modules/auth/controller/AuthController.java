package com.vhre.auth_boilerplate.modules.auth.controller;

import com.vhre.auth_boilerplate.modules.auth.dto.LoginRequestDTO;
import com.vhre.auth_boilerplate.modules.auth.dto.TokenResponseDTO;
import com.vhre.auth_boilerplate.modules.auth.service.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Endpoints for user authentication and token lifecycle management")
public class AuthController {

    private final AuthService authService;

    public ResponseEntity<TokenResponseDTO> login(@Valid @RequestBody LoginRequestDTO dto) {
        return ResponseEntity.ok(authService.login(dto));
    }
}
