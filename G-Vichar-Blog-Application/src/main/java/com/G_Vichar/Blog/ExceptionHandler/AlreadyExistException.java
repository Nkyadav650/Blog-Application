package com.G_Vichar.Blog.ExceptionHandler;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AlreadyExistException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public AlreadyExistException(String message) {
		super(message);
	}
}
