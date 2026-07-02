package com.vhre.auth_boilerplate.modules.roles.entity;

import com.vhre.auth_boilerplate.core.audit.AbstractAuditBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role extends AbstractAuditBase {
    @Column(nullable = false, unique = true, length = 50)
    private String name;
}
