package com.app.new_spa_server.domain.service.impl;

import com.app.new_spa_server.domain.Task;
import com.app.new_spa_server.domain.repository.TaskRepository;
import com.app.new_spa_server.domain.service.TaskService;

import java.util.List;
import java.util.Optional;

public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> getAll(Long userId) {
        return taskRepository.findAll(userId);
    }

    @Override
    public Optional<Task> findById(Long id, Long userId) {
        return taskRepository.findById(id, userId);
    }

    @Override
    public Task create(Task task, Long userId) {
        return taskRepository.save(task, userId);
    }

    @Override
    public Task update(Task task, Long userId) {
        if (taskRepository.existsById(task.getId(), userId)) {
            return taskRepository.save(task, userId);
        }
        return null;
    }

    @Override
    public void delete(Long id, Long userId) {
        findById(id, userId)
                .ifPresent(task -> taskRepository.remove(task, userId));
    }
}
