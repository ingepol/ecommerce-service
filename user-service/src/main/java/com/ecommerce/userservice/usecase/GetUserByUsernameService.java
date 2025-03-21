package com.ecommerce.userservice.usecase;

import com.ecommerce.common.domain.User;
import com.ecommerce.userservice.domain.repository.GetUserRepository;
import com.ecommerce.userservice.domain.usecase.GetUserUseCase;

import java.util.Optional;

public record GetUserByUsernameService(GetUserRepository getUserRepository) implements GetUserUseCase {

    @Override
    public Optional<User> execute(User user) {
        return this.getUserRepository.findByUsername(user.username());
    }
}
