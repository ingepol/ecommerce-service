package com.ecommerce.userservice.boot;

import com.ecommerce.userservice.adapters.model.UserRequest;
import com.ecommerce.userservice.domain.enums.Role;
import com.ecommerce.userservice.infrastructure.entity.UserEntity;
import com.ecommerce.userservice.infrastructure.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class CreateUserIT {

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ObjectMapper objectMapper;

	private MockMvc mockMvc;

	@BeforeEach
	void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
		this.userRepository.deleteAll(); // Clean up the repository before each test
	}

	@Test
	void givenValidUserRequest_whenRegisterUser_thenReturnUser() throws Exception {
		// Given
		final UserRequest request = new UserRequest("username", "email@example.com", "password", Role.USER.name());

		// When & Then
		this.mockMvc
				.perform(post("/api/v1/users").contentType(MediaType.APPLICATION_JSON)
						.content(this.objectMapper.writeValueAsString(request)))
				.andDo(print()).andExpect(status().isOk());

		// Additional assertions can be added to verify the user was saved in the
		// database
		final UserEntity savedUser = this.userRepository.findByUsername("username").orElseThrow();
		assertEquals("username", savedUser.getUsername());
		assertEquals("email@example.com", savedUser.getEmail());
	}
}
