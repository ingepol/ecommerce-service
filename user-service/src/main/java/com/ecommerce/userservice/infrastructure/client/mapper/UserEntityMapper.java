package com.ecommerce.userservice.infrastructure.client.mapper;

import com.ecommerce.common.domain.User;
import com.ecommerce.userservice.infrastructure.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserEntityMapper {

    default UserEntity toEntity(User user) {
        if (user == null) {
            return UserEntity.empty();
        }
        return UserEntity.of(user);
    }

    User toDomain(UserEntity userEntity);
}
