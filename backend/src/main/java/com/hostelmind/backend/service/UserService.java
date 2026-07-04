package com.hostelmind.backend.service;

import java.util.List;
import java.util.Optional;

import com.hostelmind.backend.entity.User;

public interface UserService {

    User saveUser(User user);

    List<User> getAllUsers();

    Optional<User> getUserById(Long id);

    Optional<User> getUserByEmail(String email);

    void deleteUser(Long id);
}