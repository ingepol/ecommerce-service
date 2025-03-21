package com.ecommerce.userservice.usecase;

import com.ecommerce.common.domain.User;
import com.ecommerce.userservice.domain.repository.CreateUserRepository;
import com.ecommerce.userservice.domain.usecase.CreateUserUseCase;
import org.springframework.security.crypto.password.PasswordEncoder;

public record CreateUserService(CreateUserRepository createUserRepository,
                                PasswordEncoder passwordEncoder) implements CreateUserUseCase {

    public User execute(final User user) {
        // Validate input
        if (user.email() == null || user.email().isBlank()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        if (user.password() == null || user.password().isBlank()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }

        String encryptedPassword = passwordEncoder.encode(user.password());

        User newUser = user.withPassword(encryptedPassword);

        return createUserRepository.execute(newUser);
    }

}
