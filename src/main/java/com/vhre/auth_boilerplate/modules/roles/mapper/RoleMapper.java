package com.vhre.auth_boilerplate.modules.roles.mapper;

import com.vhre.auth_boilerplate.modules.roles.dto.RoleRequestDTO;
import com.vhre.auth_boilerplate.modules.roles.dto.RoleResponseDTO;
import com.vhre.auth_boilerplate.modules.roles.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface RoleMapper {
    Role toEntity(RoleRequestDTO requestDTO);

    @Mapping(target = "created_at", source = "createdAt")
    @Mapping(target = "updated_at", source = "updatedAt")
    RoleResponseDTO toResponse(Role role);

    List<RoleResponseDTO> toResponseList(List<Role> roles);
}
