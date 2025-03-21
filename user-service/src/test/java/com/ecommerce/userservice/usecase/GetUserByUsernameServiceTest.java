package com.ecommerce.userservice.usecase;

import com.ecommerce.common.domain.User;
import com.ecommerce.userservice.domain.repository.GetUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetUserByUsernameServiceTest {

    @Mock
    private GetUserRepository getUserRepository;

    @InjectMocks
    private GetUserByUsernameService getUserByUsernameService;

    @Test
    void givenExistingUsername_whenGetUserByUsername_thenReturnUser() {
        // Given
        String username = "existingUser";
        User userFind = User.withUsername(username);
        User user = User.of(username, "email@example.com", "password", "role");
        when(getUserRepository.findByUsername(username)).thenReturn(Optional.of(userFind));

        // When
        Optional<User> foundUser = getUserByUsernameService.execute(user);

        // Then
        assertThat(foundUser).isPresent();
    }

    @Test
    void givenNonExistingUsername_whenGetUserByUsername_thenReturnEmpty() {
        // Given
        String username = "nonExistingUser";
        User userFind = User.withUsername(username);
        when(getUserRepository.findByUsername(username)).thenReturn(Optional.empty());

        // When
        Optional<User> foundUser = getUserByUsernameService.execute(userFind);

        // Then
        assertThat(foundUser).isEmpty();
    }
}
