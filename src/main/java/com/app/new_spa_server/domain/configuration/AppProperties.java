package com.app.new_spa_server.domain.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Data
@Component
@ConfigurationProperties(prefix = "app")
public class AppProperties {

    private String[] publicUrls;
    private String jwtSecret;
    private Duration jwtExpiration;

}
