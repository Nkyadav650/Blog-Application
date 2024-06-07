package com.G_Vichar.Blog.ExceptionHandler;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TokenExpiredException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public TokenExpiredException(String message) {
		super(message);
	}
	
}
