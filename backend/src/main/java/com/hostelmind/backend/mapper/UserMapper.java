package com.hostelmind.backend.mapper;

import com.hostelmind.backend.dto.UserDTO;
import com.hostelmind.backend.entity.User;

public class UserMapper {

    public static UserDTO toDTO(User user) {

        return UserDTO.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .username(user.getUsername())
                .role(user.getRole())
                .active(user.isActive())
                .build();
    }

    public static User toEntity(UserDTO dto) {

        User user = new User();

        user.setFullName(dto.getFullName());
        user.setEmail(dto.getEmail());
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setRole(dto.getRole());
        user.setActive(dto.getActive());

        return user;
    }
}