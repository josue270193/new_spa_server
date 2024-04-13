package com.app.new_spa_server.infrastructure.repository.h2.entity;

import com.app.new_spa_server.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "USERS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    @NotEmpty
    private String name;

    @Column(name = "USERNAME")
    @NotEmpty
    private String username;

    @Column(name = "PASSWORD")
    @NotEmpty
    private String password;

    @Column(name = "EMAIL")
    @NotEmpty
    private String email;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "STATUS_ID")
    @NotNull
    private StatusEntity status;

    public UserEntity(User user) {
        setId(user.getId());
        setName(user.getName());
        setUsername(user.getUsername());
        setPassword(user.getPassword());
        setEmail(user.getEmail());
        setStatus(StatusEntity.fromDomain(user.getStatus()));
    }

    public User toDomain() {
        return User.builder()
                .id(this.getId())
                .name(this.getName())
                .username(this.getUsername())
                .password(this.getPassword())
                .email(this.getEmail())
                .status(this.getStatus().toDomain())
                .build();
    }
}
