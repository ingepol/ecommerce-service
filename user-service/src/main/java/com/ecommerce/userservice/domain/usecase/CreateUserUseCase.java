package com.ecommerce.userservice.domain.usecase;

import com.ecommerce.common.domain.User;

@FunctionalInterface
public interface CreateUserUseCase {

    User execute(final User user);
}
