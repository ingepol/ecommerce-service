package com.ecommerce.userservice.usecase;

import com.ecommerce.common.domain.User;
import com.ecommerce.userservice.domain.repository.CreateUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateUserServiceTest {

    @Mock
    private CreateUserRepository createUserRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private CreateUserService createUserService;


    @Test
    void givenValidUser_whenCreateUser_thenReturnCreatedUser() {
        // Given
        User user = User.of("username", "email@example.com", "password", "role");
        String encodedPassword = "encodedPassword";
        User userWithEncodedPassword = user.withPassword(encodedPassword);
        when(createUserRepository.execute(any(User.class))).thenReturn(user);
        when(passwordEncoder.encode(user.password())).thenReturn(encodedPassword);

        // When
        User createdUser = createUserService.execute(user);

        // Then
        assertEquals(user, createdUser);
    }

    @ParameterizedTest
    @NullAndEmptySource
    void givenInvalidEmailUser_whenCreateUser_thenThrowIllegalArgumentException(String invalidValue) {
        // Given
        User user = User.of("username", invalidValue, "password", "role");

        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            createUserService.execute(user);
        });
        assertEquals("Email cannot be null or empty", exception.getMessage());
    }

    @ParameterizedTest

    @NullAndEmptySource
    void givenInvalidPasswordUser_whenCreateUser_thenThrowIllegalArgumentException(String invalidValue) {
        // Given
        User user = User.of("username", "email@example.com", invalidValue, "role");

        // When & Then
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            createUserService.execute(user);
        });
        assertEquals("Password cannot be null or empty", exception.getMessage());
    }
}
