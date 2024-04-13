package com.app.new_spa_server.application.controller;

import com.app.new_spa_server.application.mapper.UserMapper;
import com.app.new_spa_server.domain.service.UserService;
import com.app.new_spa_server.infrastructure.security.JwtTokenProvider;
import lombok.AllArgsConstructor;
import org.openapitools.api.UserApi;
import org.openapitools.model.UserDTO;
import org.openapitools.model.UserLoginDTO;
import org.openapitools.model.UserRegisterDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public/v1")
@AllArgsConstructor
public class UserController implements UserApi {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final UserService userService;
    private final UserMapper userMapper;

    @Override
    public ResponseEntity<String> loginUser(UserLoginDTO userLoginDTO) {
        var token = new UsernamePasswordAuthenticationToken(userLoginDTO.getUsername(), userLoginDTO.getPassword());
        var auth = authenticationManager.authenticate(token);
        var jwt = tokenProvider.createToken(auth);
        return ResponseEntity.ok()
                .body(jwt);
    }

    @Override
    public ResponseEntity<UserDTO> registerUser(UserRegisterDTO dto) {
        var userRegistered = userService.register(userMapper.toDomain(dto));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userMapper.toDto(userRegistered));
    }
}
