package com.ecommerce.userservice.infrastructure.entity;

import com.ecommerce.common.domain.User;
import com.ecommerce.userservice.domain.enums.Role;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "users")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class UserEntity {

    @Id
    private String id;

    private String username;

    private String email;

    private String password;

    private Role role;

    public static UserEntity empty() {
        return new UserEntity();
    }

    public static UserEntity of(User user) {
        return new UserEntity(UUID.randomUUID().toString(), user.username(), user.email(), user.password(), Role.valueOf(user.role()));
    }
}
