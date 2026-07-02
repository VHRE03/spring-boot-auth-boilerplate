package com.vhre.auth_boilerplate.modules.roles.mapper;

import com.vhre.auth_boilerplate.modules.roles.dto.RoleRequestDTO;
import com.vhre.auth_boilerplate.modules.roles.dto.RoleResponseDTO;
import com.vhre.auth_boilerplate.modules.roles.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RoleMapper {
    Role toEntity(RoleRequestDTO requestDTO);

    RoleResponseDTO toResponse(Role role);

    List<RoleResponseDTO> toResponseList(List<Role> roles);
}
