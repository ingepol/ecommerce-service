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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetUserRepositoryByUsernameDBRepositoryTest {

    @Mock
    private UserEntityMapper userEntityMapper;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private GetUserRepositoryByUsernameDBRepository getUserRepositoryByUsernameDBRepository;

    @Test
    void givenExistingUsername_whenFindByUsername_thenReturnUser() {
        // Given
        String username = "existingUser";
        User user = User.of("username", "email@example.com", "password", Role.USER.name());
        UserEntity userEntity = UserEntity.of(user);
        when(this.userRepository.findByUsername(username)).thenReturn(Optional.of(userEntity));
        when(this.userEntityMapper.toDomain(userEntity)).thenReturn(user);

        // When
        Optional<User> foundUser = getUserRepositoryByUsernameDBRepository.findByUsername(username);

        // Then
        assertEquals(Optional.of(user), foundUser);
    }

    @Test
    void givenNonExistingUsername_whenFindByUsername_thenReturnEmpty() {
        // Given
        String username = "nonExistingUser";
        when(this.userRepository.findByUsername(anyString())).thenReturn(Optional.empty());

        // When
        Optional<User> foundUser = getUserRepositoryByUsernameDBRepository.findByUsername(username);

        // Then
        assertEquals(Optional.empty(), foundUser);
    }
}
