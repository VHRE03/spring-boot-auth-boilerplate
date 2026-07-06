package com.vhre.auth_boilerplate.modules.users.service;

import com.vhre.auth_boilerplate.core.exception.ResourceNotFoundException;
import com.vhre.auth_boilerplate.modules.roles.entity.Role;
import com.vhre.auth_boilerplate.modules.roles.repository.RoleRepository;
import com.vhre.auth_boilerplate.modules.users.dto.UserRequestDTO;
import com.vhre.auth_boilerplate.modules.users.dto.UserResponseDTO;
import com.vhre.auth_boilerplate.modules.users.entity.User;
import com.vhre.auth_boilerplate.modules.users.mapper.UserMapper;
import com.vhre.auth_boilerplate.modules.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserResponseDTO create(UserRequestDTO dto) {
        if (userRepository.existsByUsername(dto.username())) {
            throw new IllegalArgumentException("User already exists with username: " + dto.username());
        }

        if (userRepository.existsByEmail(dto.email())) {
            throw new IllegalArgumentException("User already exists with email: " + dto.email());
        }

        Set<Role> roles = getRolesFromIds(dto.roleIds());

        User user = User.builder()
                .username(dto.username())
                .email(dto.email())
                .password(passwordEncoder.encode(dto.password()))
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .isEnabled(true)
                .isAccountNonLocked(true)
                .roles(roles)
                .build();

        return userMapper.toResponse(userRepository.save(user));
    }

    public List<UserResponseDTO> findAll() {
        return userMapper.toResponseList(userRepository.findAll());
    }

    public UserResponseDTO findById(UUID id) {
        return userMapper.toResponse(userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id)));
    }

    @Transactional
    public UserResponseDTO update(UUID id, UserRequestDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + id));

        if (!user.getUsername().equals(dto.username()) && userRepository.existsByUsername(dto.username())) {
            throw new IllegalArgumentException("Username is already taken: " + dto.username());
        }
        if (!user.getEmail().equals(dto.email()) && userRepository.existsByEmail(dto.email())) {
            throw new IllegalArgumentException("Email is already taken: " + dto.email());
        }

        user.setUsername(dto.username());
        user.setEmail(dto.email());
        user.setFirstName(dto.firstName());
        user.setLastName(dto.lastName());
        user.setPassword(passwordEncoder.encode(dto.password()));
        user.setRoles(getRolesFromIds(dto.roleIds()));

        return userMapper.toResponse(userRepository.save(user));
    }

    @Transactional
    public void delete(UUID id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found with ID: " + id);
        }
        userRepository.deleteById(id);
    }

    private Set<Role> getRolesFromIds(Set<UUID> roleIds) {
        return roleIds.stream()
                .map(roleId -> roleRepository.findById(roleId)
                        .orElseThrow(() -> new ResourceNotFoundException("Role not found with ID: " + roleId)))
                .collect(Collectors.toSet());
    }
}
