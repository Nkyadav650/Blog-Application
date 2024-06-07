package com.G_Vichar.Blog.ExceptionHandler;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AccessDeniedException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	public AccessDeniedException(String message) {
		super(message);
	}
}
