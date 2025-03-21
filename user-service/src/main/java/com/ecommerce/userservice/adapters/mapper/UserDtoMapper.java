package com.ecommerce.userservice.adapters.mapper;

import com.ecommerce.common.domain.User;
import com.ecommerce.userservice.model.UserDTO;
import com.ecommerce.userservice.model.UserRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserDtoMapper {

	UserDTO toDto(User user);

	User toDomain(UserRequestDTO user);
}
