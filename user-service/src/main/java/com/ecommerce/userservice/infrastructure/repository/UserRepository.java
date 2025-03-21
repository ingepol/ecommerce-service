package com.ecommerce.userservice.infrastructure.repository;

import com.ecommerce.userservice.infrastructure.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends MongoRepository<UserEntity, UUID> {

	Optional<UserEntity> findByUsername(String username);
}
