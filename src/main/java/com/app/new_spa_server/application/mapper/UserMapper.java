package com.app.new_spa_server.application.mapper;

import com.app.new_spa_server.domain.Task;
import com.app.new_spa_server.domain.User;
import lombok.AllArgsConstructor;
import org.openapitools.model.TaskDTO;
import org.openapitools.model.UserDTO;
import org.openapitools.model.UserRegisterDTO;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserMapper {

    private final StatusMapper statusMapper;

    public UserDTO toDto(User user) {
        var dto = new UserDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setStatus(statusMapper.toDto(user.getStatus()));
        return dto;
    }

    public User toDomain(UserRegisterDTO dto) {
        return User.builder()
                .name(dto.getName())
                .username(dto.getUsername())
                .password(dto.getPassword())
                .email(dto.getEmail())
                .build();
    }
}
