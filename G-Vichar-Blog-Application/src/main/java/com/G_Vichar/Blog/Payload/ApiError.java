package com.G_Vichar.Blog.Payload;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ApiError {

	private String message;
	private List<String> details;
	private HttpStatus status;
	private LocalDateTime timeStamp;
}
