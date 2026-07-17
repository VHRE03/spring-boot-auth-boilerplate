package com.vhre.auth_boilerplate.modules.tokens.verification.entity;

import com.vhre.auth_boilerplate.modules.tokens.base.entity.BaseToken;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "verification_tokens")
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class VerificationToken extends BaseToken {
}
