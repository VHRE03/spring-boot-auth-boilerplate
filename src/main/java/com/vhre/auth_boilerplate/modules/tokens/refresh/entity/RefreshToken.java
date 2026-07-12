package com.vhre.auth_boilerplate.modules.tokens.refresh.entity;

import com.vhre.auth_boilerplate.modules.tokens.base.entity.BaseToken;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "refresh_tokens")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RefreshToken extends BaseToken {
}
