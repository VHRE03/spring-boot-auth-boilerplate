package com.vhre.auth_boilerplate.modules.tokens.passwordReset.repository;

import com.vhre.auth_boilerplate.modules.tokens.passwordReset.entity.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, UUID> {
    Optional<PasswordResetToken> findByToken(String token);
}
