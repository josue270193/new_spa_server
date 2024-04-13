package com.app.new_spa_server.domain.service.impl;

import com.app.new_spa_server.domain.User;
import com.app.new_spa_server.domain.repository.UserRepository;
import com.app.new_spa_server.domain.service.UserService;

import java.util.Optional;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> loadUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User register(User user) {
        return userRepository.save(user);
    }
}
