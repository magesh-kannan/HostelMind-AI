package com.hostelmind.backend.mapper;

import com.hostelmind.backend.dto.UserDTO;
import com.hostelmind.backend.entity.User;

public class UserMapper {

    private UserMapper() {
    }

    public static UserDTO toDTO(User user) {

        if (user == null) {
            return null;
        }

        return UserDTO.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .username(user.getUsername())
                .role(user.getRole())
                .active(user.isActive())   // <-- Fixed
                .build();
    }

    public static User toEntity(UserDTO dto) {

        if (dto == null) {
            return null;
        }

        User user = new User();

        // Don't set ID. JPA generates it.
        user.setFullName(dto.getFullName());
        user.setEmail(dto.getEmail());
        user.setUsername(dto.getUsername());
        user.setRole(dto.getRole());
        user.setActive(dto.getActive());

        return user;
    }
}