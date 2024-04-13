package com.app.new_spa_server.infrastructure.configuration;

import com.app.new_spa_server.domain.repository.TaskRepository;
import com.app.new_spa_server.domain.repository.UserRepository;
import com.app.new_spa_server.domain.service.TaskService;
import com.app.new_spa_server.domain.service.UserService;
import com.app.new_spa_server.domain.service.impl.TaskServiceImpl;
import com.app.new_spa_server.domain.service.impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BeanConfiguration {

    @Bean
    public TaskService taskService(
            TaskRepository taskRepository
    ) {
        return new TaskServiceImpl(taskRepository);
    }

    @Bean
    public UserService userService(
            UserRepository taskRepository
    ) {
        return new UserServiceImpl(taskRepository);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
