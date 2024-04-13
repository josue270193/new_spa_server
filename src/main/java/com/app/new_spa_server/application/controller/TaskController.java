package com.app.new_spa_server.application.controller;

import com.app.new_spa_server.application.mapper.TaskMapper;
import com.app.new_spa_server.domain.User;
import com.app.new_spa_server.domain.exception.AppNotFoundException;
import com.app.new_spa_server.domain.service.TaskService;
import com.app.new_spa_server.domain.service.UserService;
import lombok.AllArgsConstructor;
import org.openapitools.api.TaskApi;
import org.openapitools.model.TaskDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/auth/v1")
@AllArgsConstructor
public class TaskController implements TaskApi {

    private final TaskService taskService;
    private final TaskMapper taskMapper;
    private final UserService userService;

    @Override
    public ResponseEntity<List<TaskDTO>> getTask() {
        var user = getUserLogged();
        var body = taskService.getAll(user.getId())
                .stream()
                .map(taskMapper::toDto)
                .toList();
        return ResponseEntity.ok()
                .body(body);
    }

    @Override
    public ResponseEntity<TaskDTO> getTaskById(Long taskId) {
        var user = getUserLogged();
        var body = taskService.findById(taskId, user.getId())
                .map(taskMapper::toDto)
                .orElseThrow(() -> new AppNotFoundException("Task not found"));
        return ResponseEntity.ok()
                .body(body);
    }

    @Override
    public ResponseEntity<TaskDTO> addTask(TaskDTO taskDTO) {
        var user = getUserLogged();
        var taskCreated = taskService.create(taskMapper.toDomain(taskDTO), user.getId());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(taskMapper.toDto(taskCreated));
    }

    @Override
    public ResponseEntity<TaskDTO> updateTask(TaskDTO taskDTO) {
        var user = getUserLogged();
        var taskUpdated = taskService.update(taskMapper.toDomain(taskDTO), user.getId());
        return ResponseEntity.ok()
                .body(taskMapper.toDto(taskUpdated));
    }

    @Override
    public ResponseEntity<Void> deleteTask(Long taskId) {
        var user = getUserLogged();
        taskService.delete(taskId, user.getId());
        return ResponseEntity.ok()
                .build();
    }

    private User getUserLogged() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        return userService.loadUserByUsername(auth.getName())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
