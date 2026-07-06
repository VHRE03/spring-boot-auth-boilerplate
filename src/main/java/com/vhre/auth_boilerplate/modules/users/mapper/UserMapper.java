package com.vhre.auth_boilerplate.modules.users.mapper;

import com.vhre.auth_boilerplate.modules.roles.mapper.RoleMapper;
import com.vhre.auth_boilerplate.modules.users.dto.UserResponseDTO;
import com.vhre.auth_boilerplate.modules.users.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.ERROR,
        uses = {RoleMapper.class}
)
public interface UserMapper {

    @Mapping(target = "created_at", source = "createdAt")
    @Mapping(target = "updated_at", source = "updatedAt")
    UserResponseDTO toResponse(User user);

    List<UserResponseDTO> toResponseList(List<User> users);
}
