package com.ecommerce.userservice.domain.repository;

import com.ecommerce.common.domain.User;

import java.util.Optional;

@FunctionalInterface
public interface GetUserRepository {

    Optional<User> findByUsername(final String username);
}
