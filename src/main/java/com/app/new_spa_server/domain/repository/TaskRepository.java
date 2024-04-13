package com.app.new_spa_server.domain.repository;

import com.app.new_spa_server.domain.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {

    List<Task> findAll(Long userId);

    boolean existsById(Long id, Long userId);

    Optional<Task> findById(Long id, Long userId);

    Task save(Task task, Long userId);

    void remove(Task task, Long userId);

}
