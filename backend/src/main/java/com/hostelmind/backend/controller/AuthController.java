package com.hostelmind.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hostelmind.backend.dto.LoginRequestDTO;
import com.hostelmind.backend.dto.LoginResponseDTO;
import com.hostelmind.backend.dto.UserDTO;
import com.hostelmind.backend.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public UserDTO register(@RequestBody UserDTO userDTO) {
        return authService.register(userDTO);
    }

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO request) {
        return authService.login(request);
    }
}