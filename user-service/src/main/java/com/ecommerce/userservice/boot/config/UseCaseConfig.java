package com.ecommerce.userservice.boot.config;

import com.ecommerce.userservice.domain.repository.CreateUserRepository;
import com.ecommerce.userservice.domain.repository.GetUserRepository;
import com.ecommerce.userservice.domain.usecase.CreateUserUseCase;
import com.ecommerce.userservice.domain.usecase.GetUserUseCase;
import com.ecommerce.userservice.usecase.CreateUserService;
import com.ecommerce.userservice.usecase.GetUserByUsernameService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UseCaseConfig {

	@Bean
	public CreateUserUseCase createUserUseCase(CreateUserRepository createUserRepository,
			PasswordEncoder passwordEncoder) {
		return new CreateUserService(createUserRepository, passwordEncoder);
	}

	@Bean
	public GetUserUseCase getUserByUsernameService(GetUserRepository getUserRepository) {
		return new GetUserByUsernameService(getUserRepository);
	}
}
