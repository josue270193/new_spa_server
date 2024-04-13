package com.app.new_spa_server.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Status {

    private Long id;
    private String name;
    private String entity;

}
