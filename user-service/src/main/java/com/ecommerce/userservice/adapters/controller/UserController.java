package com.ecommerce.userservice.adapters.controller;

import com.ecommerce.common.domain.User;
import com.ecommerce.userservice.adapters.mapper.UserDtoMapper;
import com.ecommerce.userservice.api.UserApi;
import com.ecommerce.userservice.domain.usecase.CreateUserUseCase;
import com.ecommerce.userservice.domain.usecase.GetUserUseCase;
import com.ecommerce.userservice.model.UserDTO;
import com.ecommerce.userservice.model.UserRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

	private final CreateUserUseCase createUserUseCase;

	private final GetUserUseCase getUserByUsername;

	private final UserDtoMapper mapper;

	@Override
	public ResponseEntity<UserDTO> getUserByUsername(String username) {
		final var user = User.withUsername(username);
		return this.getUserByUsername.execute(user).map(this.mapper::toDto).map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@Override
	public ResponseEntity<UserDTO> registerUser(UserRequestDTO userRequestDTO) {
		final var user = this.mapper.toDomain(userRequestDTO);
		final var newUser = this.createUserUseCase.execute(user);
		final var response = this.mapper.toDto(newUser);
		return ResponseEntity.ok().body(response);
	}

}
