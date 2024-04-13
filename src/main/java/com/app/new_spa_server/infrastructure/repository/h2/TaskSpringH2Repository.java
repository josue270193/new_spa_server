package com.app.new_spa_server.infrastructure.repository.h2;

import com.app.new_spa_server.infrastructure.repository.h2.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskSpringH2Repository extends JpaRepository<TaskEntity, Long> {

    List<TaskEntity> findAllByUserId(Long userId);

    boolean existsByIdAndUserId(Long id, Long userId);

    Optional<TaskEntity> findByIdAndUserId(Long id, Long userId);

}
