package com.vhre.auth_boilerplate.modules.tokens.passwordReset.service;

import com.vhre.auth_boilerplate.core.exception.ResourceNotFoundException;
import com.vhre.auth_boilerplate.modules.tokens.passwordReset.entity.PasswordResetToken;
import com.vhre.auth_boilerplate.modules.tokens.passwordReset.repository.PasswordResetTokenRepository;
import com.vhre.auth_boilerplate.modules.users.entity.User;
import com.vhre.auth_boilerplate.modules.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PasswordResetTokenService {

    private final PasswordResetTokenRepository resetTokenRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void createPasswordResetToken(PasswordResetRequestDTO dto) {
        User user = userRepository.findByEmail(dto.email())
                .orElseThrow(() -> new ResourceNotFoundException("No user found with that email address"));

        PasswordResetToken resetToken = PasswordResetToken.builder()
                .user(user)
                .token(UUID.randomUUID().toString())
                .expiryDate(LocalDateTime.now().plusHours(1))
                .build();

        resetTokenRepository.save(resetToken);

        // TODO: Pendiente integrar servicio de Mailing (JavaMailSender o SendGrid)
    }

    public void resetPassword(NewPasswordRequestDTO dto) {
        PasswordResetToken resetToken = resetTokenRepository.findByToken(dto.token());
    }
}
