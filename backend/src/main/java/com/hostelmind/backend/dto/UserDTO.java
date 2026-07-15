package com.hostelmind.backend.dto;

import com.hostelmind.backend.enums.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotBlank
    private String fullName;

    @Email
    private String email;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotNull
    private Role role;

    @NotNull
    private Boolean active;
}