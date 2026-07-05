package com.hostelmind.backend.service;

import java.util.List;
import java.util.Optional;

import com.hostelmind.backend.dto.UserDTO;

public interface UserService {

    UserDTO saveUser(UserDTO userDTO);

    List<UserDTO> getAllUsers();

    Optional<UserDTO> getUserById(Long id);

    Optional<UserDTO> getUserByEmail(String email);

    void deleteUser(Long id);
}