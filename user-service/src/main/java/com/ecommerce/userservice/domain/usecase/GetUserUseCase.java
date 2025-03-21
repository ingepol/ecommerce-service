package com.ecommerce.userservice.domain.usecase;

import com.ecommerce.common.domain.User;

import java.util.Optional;

@FunctionalInterface
public interface GetUserUseCase {

    Optional<User> execute(final User user);
}
