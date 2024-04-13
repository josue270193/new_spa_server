package com.app.new_spa_server.infrastructure.repository.h2;

import com.app.new_spa_server.domain.Task;
import com.app.new_spa_server.domain.repository.TaskRepository;
import com.app.new_spa_server.infrastructure.repository.h2.entity.TaskEntity;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Primary
@AllArgsConstructor
public class TaskH2Repository implements TaskRepository {

    private final TaskSpringH2Repository repository;
    private final UserSpringH2Repository userRepository;
    private final StatusSpringH2Repository statusRepository;

    @Override
    public List<Task> findAll(Long userId) {
        return repository.findAllByUserId(userId)
                .stream()
                .map(TaskEntity::toDomain)
                .toList();
    }

    @Override
    public boolean existsById(Long id, Long userId) {
        return repository.existsByIdAndUserId(id, userId);
    }

    @Override
    public Optional<Task> findById(Long id, Long userId) {
        return repository.findByIdAndUserId(id, userId)
                .map(TaskEntity::toDomain);
    }

    @Override
    public Task save(Task task, Long userId) {
        var user = userRepository.findById(userId)
                .orElse(null);
        var status = statusRepository.findById(task.getStatus().getId())
                .orElse(null);

        var entity = new TaskEntity(task);
        entity.setUser(user);
        entity.setStatus(status);
        return repository.save(entity)
                .toDomain();
    }

    @Override
    public void remove(Task task, Long userId) {
        repository.delete(new TaskEntity(task));
    }
}
