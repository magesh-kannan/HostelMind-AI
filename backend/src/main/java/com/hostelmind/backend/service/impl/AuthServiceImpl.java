package com.hostelmind.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hostelmind.backend.dto.LoginRequestDTO;
import com.hostelmind.backend.dto.LoginResponseDTO;
import com.hostelmind.backend.dto.UserDTO;
import com.hostelmind.backend.entity.User;
import com.hostelmind.backend.mapper.UserMapper;
import com.hostelmind.backend.repository.UserRepository;
import com.hostelmind.backend.security.JwtService;
import com.hostelmind.backend.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDTO register(UserDTO userDTO) {

        if (userRepository.findByUsername(userDTO.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        User user = UserMapper.toEntity(userDTO);

        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        User saved = userRepository.save(user);

        return UserMapper.toDTO(saved);
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()));

        User user = userRepository
                .findByUsername(request.getUsername())
                .orElseThrow();

        String token = jwtService.generateToken(user.getUsername());

        return LoginResponseDTO.builder()
                .token(token)
                .username(user.getUsername())
                .role(user.getRole().name())
                .build();
    }
}