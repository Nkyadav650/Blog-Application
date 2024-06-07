package com.G_Vichar.Blog.ExceptionHandler;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class IdNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public IdNotFoundException(String message) {
		super(message);
	}
}
