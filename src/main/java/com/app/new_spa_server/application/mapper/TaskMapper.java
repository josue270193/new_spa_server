package com.app.new_spa_server.application.mapper;

import com.app.new_spa_server.domain.Task;
import lombok.AllArgsConstructor;
import org.openapitools.model.TaskDTO;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TaskMapper {

    private final StatusMapper statusMapper;

    public TaskDTO toDto(Task task) {
        var dto = new TaskDTO();
        dto.setId(task.getId());
        dto.setName(task.getName());
        dto.setStatus(statusMapper.toDto(task.getStatus()));
        return dto;
    }

    public Task toDomain(TaskDTO dto) {
        return Task.builder()
                .id(dto.getId())
                .name(dto.getName())
                .status(statusMapper.toDomain(dto.getStatus()))
                .build();
    }
}
