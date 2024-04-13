package com.app.new_spa_server.application.mapper;

import com.app.new_spa_server.domain.Status;
import org.openapitools.model.StatusDTO;
import org.springframework.stereotype.Component;

@Component
public class StatusMapper {

    public StatusDTO toDto(Status status) {
        if (status == null) {
            return null;
        }

        var dto = new StatusDTO();
        dto.setId(status.getId());
        dto.setName(status.getName());
        dto.setEntity(status.getEntity());
        return dto;
    }

    public Status toDomain(StatusDTO dto) {
        if (dto == null) {
            return null;
        }

        return Status.builder()
                .id(dto.getId())
                .name(dto.getName())
                .entity(dto.getEntity())
                .build();
    }
}
