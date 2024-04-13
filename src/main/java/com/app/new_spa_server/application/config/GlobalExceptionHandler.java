package com.app.new_spa_server.application.config;

import com.app.new_spa_server.domain.exception.AppNotFoundException;
import lombok.RequiredArgsConstructor;
import org.openapitools.model.ApiResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@RequiredArgsConstructor
@ControllerAdvice(annotations = Controller.class)
public class GlobalExceptionHandler {

    @ExceptionHandler(AppNotFoundException.class)
    public ResponseEntity<ApiResponseDTO> handleAppNotFoundException(AppNotFoundException ex) {
        return generateBody(ex, ex.getStatus());
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ApiResponseDTO> handleUsernameNotFoundException(UsernameNotFoundException ex) {
        var httpStatus = HttpStatus.NOT_FOUND;
        return generateBody(ex, httpStatus);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponseDTO> handleException(Exception ex) {
        var httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        return generateBody(ex, httpStatus);
    }

    private ResponseEntity<ApiResponseDTO> generateBody(Exception ex, HttpStatus httpStatus) {
        var body = new ApiResponseDTO();
        body.setCode(httpStatus.value());
        body.setMessage(ex.getMessage());

        return ResponseEntity.status(httpStatus)
                .body(body);
    }

}
