package com.ecommerce.userservice.infrastructure.client.mapper;

import com.ecommerce.common.domain.User;
import com.ecommerce.userservice.domain.enums.Role;
import com.ecommerce.userservice.infrastructure.entity.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserEntityMapperTest {

    private UserEntityMapper userEntityMapper;

    @BeforeEach
    void setUp() {
        userEntityMapper = Mappers.getMapper(UserEntityMapper.class);
    }

    @Test
    void givenUserToEntity_whenMaps_thenCorrect() {
        // Given
        User user = User.of("username", "email@example.com", "password", Role.USER.name());

        // When
        UserEntity userEntity = userEntityMapper.toEntity(user);

        // Then
        assertEquals(user.username(), userEntity.getUsername());
        assertEquals(user.email(), userEntity.getEmail());
        assertEquals(user.password(), userEntity.getPassword());
        assertEquals(user.role(), userEntity.getRole().name());
    }

    @Test
    void givenEntityToUser_whenMaps_thenCorrect() {
        // Given
        User user = User.of("username", "email@example.com", "password", Role.USER.name());
        UserEntity userEntity = UserEntity.of(user);

        // When
        User result = userEntityMapper.toDomain(userEntity);

        // Then
        assertEquals(userEntity.getUsername(), result.username());
        assertEquals(userEntity.getEmail(), result.email());
        assertEquals(userEntity.getPassword(), result.password());
        assertEquals(userEntity.getRole().name(), result.role());
    }
}
