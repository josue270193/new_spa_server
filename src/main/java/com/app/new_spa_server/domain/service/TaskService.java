package com.app.new_spa_server.domain.service;

import com.app.new_spa_server.domain.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    List<Task> getAll(Long userId);

    Optional<Task> findById(Long id, Long userId);

    Task create(Task task, Long userId);

    Task update(Task task, Long userId);

    void delete(Long id, Long userId);

}
