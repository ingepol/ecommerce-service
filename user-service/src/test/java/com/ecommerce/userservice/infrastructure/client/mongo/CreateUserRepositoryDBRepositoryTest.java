package com.ecommerce.userservice.infrastructure.client.mongo;

import com.ecommerce.common.domain.User;
import com.ecommerce.userservice.domain.enums.Role;
import com.ecommerce.userservice.infrastructure.client.mapper.UserEntityMapper;
import com.ecommerce.userservice.infrastructure.entity.UserEntity;
import com.ecommerce.userservice.infrastructure.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateUserRepositoryDBRepositoryTest {

    @Mock
    private UserEntityMapper userEntityMapper;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CreateUserRepositoryDBRepository createUserRepositoryDBRepository;

    @Test
    void givenValidUser_whenSaveUser_thenReturnSavedUser() {
        // Given
        User user = User.of("username", "email@example.com", "password", Role.USER.name());
        UserEntity userEntity = UserEntity.of(user);
        when(this.userEntityMapper.toEntity(user)).thenReturn(userEntity);
        when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);
        when(this.userEntityMapper.toDomain(userEntity)).thenReturn(user);

        // When
        User savedUser = createUserRepositoryDBRepository.execute(user);

        // Then
        assertEquals(user, savedUser);
    }
}
