package com.vhre.auth_boilerplate.modules.auth.service;

import com.vhre.auth_boilerplate.core.exception.ResourceNotFoundException;
import com.vhre.auth_boilerplate.core.security.service.JwtService;
import com.vhre.auth_boilerplate.modules.auth.dto.LoginRequestDTO;
import com.vhre.auth_boilerplate.modules.auth.dto.TokenResponseDTO;
import com.vhre.auth_boilerplate.modules.tokens.refresh.entity.RefreshToken;
import com.vhre.auth_boilerplate.modules.tokens.refresh.service.RefreshTokenService;
import com.vhre.auth_boilerplate.modules.users.entity.User;
import com.vhre.auth_boilerplate.modules.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;

    public TokenResponseDTO login(LoginRequestDTO requestDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        requestDTO.username(),
                        requestDTO.password()
                )
        );

        User user = userRepository.findByUsername(requestDTO.username())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        String accessToken = jwtService.generateToken(user);

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user.getId());

        return new TokenResponseDTO(accessToken, refreshToken.getToken());
    }
}
