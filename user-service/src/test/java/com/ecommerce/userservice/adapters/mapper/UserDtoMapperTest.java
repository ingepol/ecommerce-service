package com.ecommerce.userservice.adapters.mapper;

import com.ecommerce.common.domain.User;
import com.ecommerce.userservice.domain.enums.Role;
import com.ecommerce.userservice.model.UserDTO;
import com.ecommerce.userservice.model.UserRequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserDtoMapperTest {

	private UserDtoMapper userDtoMapper;

	@BeforeEach
	void setUp() {
		this.userDtoMapper = Mappers.getMapper(UserDtoMapper.class);
	}

	@Test
	void givenUserToEntity_whenMaps_thenCorrect() {
		// Given
		final User user = User.of("username", "email@example.com", "password", Role.USER.name());

		// When
		final UserDTO userDTO = this.userDtoMapper.toDto(user);

		// Then
		assertEquals(user.username(), userDTO.getUsername());
		assertEquals(user.email(), userDTO.getEmail());
		assertEquals(user.password(), userDTO.getPassword());
		assertEquals(user.role(), userDTO.getRole());
	}

	@Test
	void givenEntityToUser_whenMaps_thenCorrect() {
		// Given
		final UserRequestDTO user = new UserRequestDTO();
		user.setUsername("username");
		user.setEmail("email@example.com");
		user.setPassword("password");
		user.setRole(Role.USER.name());

		// When
		final User result = this.userDtoMapper.toDomain(user);

		// Then
		assertEquals(user.getUsername(), result.username());
		assertEquals(user.getEmail(), result.email());
		assertEquals(user.getPassword(), result.password());
		assertEquals(user.getRole(), result.role());
	}
}
