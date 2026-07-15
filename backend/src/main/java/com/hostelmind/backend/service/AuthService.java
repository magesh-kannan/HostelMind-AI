package com.hostelmind.backend.service;

import com.hostelmind.backend.dto.LoginRequestDTO;
import com.hostelmind.backend.dto.LoginResponseDTO;
import com.hostelmind.backend.dto.UserDTO;

public interface AuthService {

    UserDTO register(UserDTO userDTO);

    LoginResponseDTO login(LoginRequestDTO request);

}