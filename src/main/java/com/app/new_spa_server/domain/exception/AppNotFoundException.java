package com.app.new_spa_server.domain.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AppNotFoundException extends RuntimeException {

    private final HttpStatus status = HttpStatus.NOT_FOUND;

    public AppNotFoundException(String message) {
        super(message);
    }
}
