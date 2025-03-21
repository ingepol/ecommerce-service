package com.ecommerce.userservice.infrastructure.client.mongo;

import com.ecommerce.common.domain.User;
import com.ecommerce.userservice.domain.repository.CreateUserRepository;
import com.ecommerce.userservice.infrastructure.client.mapper.UserEntityMapper;
import com.ecommerce.userservice.infrastructure.entity.UserEntity;
import com.ecommerce.userservice.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CreateUserRepositoryDBRepository implements CreateUserRepository {

    private final UserEntityMapper mapper;

    private final UserRepository repository;

    @Override
    public User execute(User user) {
        final UserEntity entity = this.mapper.toEntity(user);
        final UserEntity entitySaved = this.repository.save(entity);
        return this.mapper.toDomain(entitySaved);
    }
}
