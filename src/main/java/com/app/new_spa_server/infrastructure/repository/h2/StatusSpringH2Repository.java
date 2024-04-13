package com.app.new_spa_server.infrastructure.repository.h2;

import com.app.new_spa_server.infrastructure.repository.h2.entity.StatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatusSpringH2Repository extends JpaRepository<StatusEntity, Long> {

    Optional<StatusEntity> findByNameAndEntity(String name, String entity);

}
