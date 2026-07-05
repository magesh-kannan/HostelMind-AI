package com.hostelmind.backend.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hostelmind.backend.dto.UserDTO;
import com.hostelmind.backend.entity.User;
import com.hostelmind.backend.mapper.UserMapper;
import com.hostelmind.backend.repository.UserRepository;
import com.hostelmind.backend.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
public UserDTO saveUser(UserDTO userDTO) {

    User user = UserMapper.toEntity(userDTO);

    User savedUser = userRepository.save(user);

    return UserMapper.toDTO(savedUser);
}

    @Override
public List<UserDTO> getAllUsers() {
    return userRepository.findAll()
            .stream()
            .map(UserMapper::toDTO)
            .toList();
}

    @Override
public Optional<UserDTO> getUserById(Long id) {
    return userRepository.findById(id)
            .map(UserMapper::toDTO);
}

    @Override
public Optional<UserDTO> getUserByEmail(String email) {
    return userRepository.findByEmail(email)
            .map(UserMapper::toDTO);
}

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}