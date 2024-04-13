package com.app.new_spa_server.infrastructure.repository.h2;

import com.app.new_spa_server.infrastructure.repository.h2.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserSpringH2Repository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUsername(String username);

}
