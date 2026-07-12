package com.vhre.auth_boilerplate.modules.tokens.verification.entity;

import com.vhre.auth_boilerplate.modules.tokens.base.entity.BaseToken;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "verification_tokens")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VerificationToken extends BaseToken {
}
