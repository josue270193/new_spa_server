package com.app.new_spa_server.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {

    private Long id;
    private String name;
    private String username;
    private String password;
    private String email;
    private Status status;

}
