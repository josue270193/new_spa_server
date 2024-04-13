package com.app.new_spa_server.domain.repository;

import com.app.new_spa_server.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    List<User> findAll();

    boolean existsById(Long id);

    Optional<User> findById(Long id);

    Optional<User> findByUsername(String username);

    User save(User user);

    void remove(User user);

}
