package com.app.new_spa_server.domain.service;

import com.app.new_spa_server.domain.User;

import java.util.Optional;

public interface UserService {

    Optional<User> loadUserByUsername(String username);

    User register(User user);

}
