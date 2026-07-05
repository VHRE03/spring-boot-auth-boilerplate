package com.vhre.auth_boilerplate.modules.roles.service;

import com.vhre.auth_boilerplate.core.exception.ResourceNotFoundException;
import com.vhre.auth_boilerplate.modules.roles.dto.RoleRequestDTO;
import com.vhre.auth_boilerplate.modules.roles.dto.RoleResponseDTO;
import com.vhre.auth_boilerplate.modules.roles.entity.Role;
import com.vhre.auth_boilerplate.modules.roles.mapper.RoleMapper;
import com.vhre.auth_boilerplate.modules.roles.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Transactional
    public RoleResponseDTO create(RoleRequestDTO dto) {
        if (roleRepository.existsByName(dto.name())) {
            throw new IllegalArgumentException("Role already exists with name: " + dto.name());
        }
        Role role = roleMapper.toEntity(dto);
        return roleMapper.toResponse(roleRepository.save(role));
    }

    public List<RoleResponseDTO> findAll() {
        return roleMapper.toResponseList(roleRepository.findAll());
    }

    public RoleResponseDTO findById(UUID id) {
        return roleMapper.toResponse(roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with ID: " + id)));
    }

    @Transactional
    public RoleResponseDTO update(UUID id, RoleRequestDTO dto) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role not found with ID: " + id));

        role.setName(dto.name());
        return roleMapper.toResponse(roleRepository.save(role));
    }

    @Transactional
    public void delete(UUID id) {
        if (!roleRepository.existsById(id)) {
            throw new ResourceNotFoundException("Role not found with ID: " + id);
        }
        roleRepository.deleteById(id);
    }
}