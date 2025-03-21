package com.ecommerce.userservice.adapters.controller;

import com.ecommerce.common.domain.User;
import com.ecommerce.userservice.adapters.mapper.UserDtoMapper;
import com.ecommerce.userservice.adapters.mapper.UserDtoMapperImpl;
import com.ecommerce.userservice.domain.usecase.CreateUserUseCase;
import com.ecommerce.userservice.domain.usecase.GetUserUseCase;
import com.ecommerce.userservice.model.UserDTO;
import com.ecommerce.userservice.model.UserRequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

	@Mock
	private CreateUserUseCase createUserUseCase;

	@Mock
	private GetUserUseCase getUserByUsername;

	private UserDtoMapper userDtoMapper;

	private UserController userController;

	@BeforeEach
	void setUp() {
		this.userDtoMapper = new UserDtoMapperImpl();
		this.userController = new UserController(this.createUserUseCase, this.getUserByUsername, this.userDtoMapper);
	}

	@Test
	void givenValidUserRequest_whenRegisterUser_thenReturnUser() {
		// Given
		final UserRequestDTO request = new UserRequestDTO();
		request.username("username");
		request.email("email@example.com");
		request.email("password");
		request.role("role");
		final User user = User.of("username", "email@example.com", "password", "role");
		when(this.createUserUseCase.execute(any(User.class))).thenReturn(user);

		// When
		final ResponseEntity<UserDTO> response = this.userController.registerUser(request);

		// Then
		assertThat(response).isNotNull();
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isNotNull();
		final UserDTO userDTO = response.getBody();
		assertThat(userDTO.getUsername()).isEqualTo(user.username());
		assertThat(userDTO.getEmail()).isEqualTo(user.email());
		assertThat(userDTO.getPassword()).isEqualTo(user.password());
		assertThat(userDTO.getRole()).isEqualTo(user.role());
	}

	@Test
	void givenExistingUsername_whenGetUserByUsername_thenReturnUser() {
		// Given
		final String username = "existingUser";
		final User user = User.withUsername(username);
		when(this.getUserByUsername.execute(any(User.class))).thenReturn(Optional.of(user));

		// When
		final ResponseEntity<UserDTO> response = this.userController.getUserByUsername(username);

		// Then
		assertThat(response).isNotNull();
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(response.getBody()).isNotNull();
		final UserDTO userDTO = response.getBody();
		assertThat(userDTO.getUsername()).isEqualTo(username);
	}

	@Test
	void givenNonExistingUsername_whenGetUserByUsername_thenReturnNotFound() {
		// Given
		final String username = "nonExistingUser";
		when(this.getUserByUsername.execute(any(User.class))).thenReturn(Optional.empty());

		// When
		final ResponseEntity<UserDTO> response = this.userController.getUserByUsername(username);

		// Then
		assertEquals(ResponseEntity.notFound().build(), response);
	}

}
