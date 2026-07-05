package com.hostelmind.backend.dto;

import com.hostelmind.backend.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

    private Long id;

    private String fullName;

    private String email;

    private String username;

    private Role role;

    private Boolean active;
}