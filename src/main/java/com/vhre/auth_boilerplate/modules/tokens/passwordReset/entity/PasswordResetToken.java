package com.vhre.auth_boilerplate.modules.tokens.passwordReset.entity;

import com.vhre.auth_boilerplate.modules.tokens.base.entity.BaseToken;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "password_reset_tokens")
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class PasswordResetToken extends BaseToken {
}
