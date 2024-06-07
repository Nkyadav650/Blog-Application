package com.G_Vichar.Blog.ExceptionHandler;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InvalidTokenException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public InvalidTokenException(String message) {
		super(message);
	}
}
