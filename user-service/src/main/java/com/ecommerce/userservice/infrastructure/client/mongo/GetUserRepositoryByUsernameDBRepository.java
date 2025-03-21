package com.ecommerce.userservice.infrastructure.client.mongo;

import com.ecommerce.common.domain.User;
import com.ecommerce.userservice.domain.repository.GetUserRepository;
import com.ecommerce.userservice.infrastructure.client.mapper.UserEntityMapper;
import com.ecommerce.userservice.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class GetUserRepositoryByUsernameDBRepository implements GetUserRepository {

    private final UserEntityMapper mapper;

    private final UserRepository repository;

    @Override
    public Optional<User> findByUsername(String username) {
        return this.repository.findByUsername(username)
            .map(this.mapper::toDomain);
    }
}
