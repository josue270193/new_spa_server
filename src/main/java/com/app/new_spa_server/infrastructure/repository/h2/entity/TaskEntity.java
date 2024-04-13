package com.app.new_spa_server.infrastructure.repository.h2.entity;

import com.app.new_spa_server.domain.Task;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "TASKS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_sequence")
    @SequenceGenerator(name = "task_sequence", sequenceName = "task_sequence", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    @NotEmpty
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "STATUS_ID")
    @NotNull
    private StatusEntity status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "USER_ID")
    @NotNull
    private UserEntity user;

    public TaskEntity(Task task) {
        setId(task.getId());
        setName(task.getName());
        setStatus(StatusEntity.fromDomain(task.getStatus()));
    }

    public Task toDomain() {
        return Task.builder()
                .id(this.getId())
                .name(this.getName())
                .status(this.getStatus().toDomain())
                .build();
    }
}
