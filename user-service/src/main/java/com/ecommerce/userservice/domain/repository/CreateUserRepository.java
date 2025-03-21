package com.ecommerce.userservice.domain.repository;

import com.ecommerce.common.domain.User;

@FunctionalInterface
public interface CreateUserRepository {

    User execute(final User user);
}
