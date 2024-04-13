package com.app.new_spa_server.infrastructure.repository.h2.entity;

import com.app.new_spa_server.domain.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "STATUS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StatusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "status_sequence")
    @SequenceGenerator(name = "status_sequence", sequenceName = "status_sequence", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    @NotEmpty
    private String name;

    @Column(name = "ENTITY")
    @NotEmpty
    private String entity;

    public StatusEntity(Status status) {
        setId(status.getId());
        setName(status.getName());
        setEntity(status.getEntity());
    }

    public static StatusEntity fromDomain(Status status) {
        if (status == null) {
            return null;
        }
        return new StatusEntity(status);
    }

    public Status toDomain() {
        return Status.builder()
                .id(this.getId())
                .name(this.getName())
                .entity(this.getEntity())
                .build();
    }
}
