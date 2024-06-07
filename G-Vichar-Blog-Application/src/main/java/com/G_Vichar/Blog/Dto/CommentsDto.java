package com.G_Vichar.Blog.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentsDto {
	
	private long id;
	private String content;
	private UserDto user;
}
