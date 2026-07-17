package com.vhre.auth_boilerplate.modules.tokens.verification.service;

import com.vhre.auth_boilerplate.core.exception.ResourceNotFoundException;
import com.vhre.auth_boilerplate.modules.tokens.verification.dto.VerifyAccountRequestDTO;
import com.vhre.auth_boilerplate.modules.tokens.verification.entity.VerificationToken;
import com.vhre.auth_boilerplate.modules.tokens.verification.repository.VerificationTokenRepository;
import com.vhre.auth_boilerplate.modules.users.entity.User;
import com.vhre.auth_boilerplate.modules.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class VerificationTokenService {

    private final VerificationTokenRepository verificationTokenRepository;
    private final UserRepository userRepository;

    public void verifyAccount(VerifyAccountRequestDTO dto) {
        VerificationToken verificationToken = verificationTokenRepository.findByToken(dto.token()).
                orElseThrow(() -> new ResourceNotFoundException("Invalid verification token"));

        if (verificationToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            verificationTokenRepository.delete(verificationToken);
            throw new RuntimeException("Verification token has expired. Please request a new one.");
        }

        User user = verificationToken.getUser();
        user.setEnabled(true);
        userRepository.save(user);

        verificationTokenRepository.delete(verificationToken);
    }
}
