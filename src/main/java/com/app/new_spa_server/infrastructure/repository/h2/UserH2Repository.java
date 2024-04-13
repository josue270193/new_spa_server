package com.app.new_spa_server.infrastructure.repository.h2;

import com.app.new_spa_server.domain.Status;
import com.app.new_spa_server.domain.User;
import com.app.new_spa_server.domain.repository.UserRepository;
import com.app.new_spa_server.infrastructure.repository.h2.entity.StatusEntity;
import com.app.new_spa_server.infrastructure.repository.h2.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Primary
@AllArgsConstructor
public class UserH2Repository implements UserRepository {

    private final UserSpringH2Repository repository;
    private final StatusSpringH2Repository statusRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<User> findAll() {
        return repository.findAll()
                .stream()
                .map(UserEntity::toDomain)
                .toList();
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public Optional<User> findById(Long id) {
        return repository.findById(id)
                .map(UserEntity::toDomain);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return repository.findByUsername(username)
                .map(UserEntity::toDomain);
    }

    @Override
    public User save(User user) {
        var encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        user.setStatus(getStatusActive());
        var entity = new UserEntity(user);
        return repository.save(entity)
                .toDomain();
    }

    private Status getStatusActive() {
        return statusRepository.findByNameAndEntity("ACTIVE", "USER")
                .map(StatusEntity::toDomain)
                .orElse(null);
    }

    @Override
    public void remove(User user) {
        repository.delete(new UserEntity(user));
    }
}
