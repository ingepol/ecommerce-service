package com.ecommerce.common.exception;

import java.io.Serial;

public class UserNotFoundException extends RuntimeException {

	@Serial
	private static final long serialVersionUID = 6183915769096659933L;

	public UserNotFoundException(String message) {
		super(message);
	}
}
