package com.G_Vichar.Blog.Dto;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.G_Vichar.Blog.Entity.CategoryEntity;
import com.G_Vichar.Blog.Entity.Likes;
import com.G_Vichar.Blog.Entity.Share;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostsDto {

	private long postId;
	private String title;
	private String content;
	private String imageName;
	private LocalDateTime timeStamp;
	private CategoryEntity category;
	private UserDto user;
	private Set<CommentsDto> comment =new HashSet<>();
	private Set<Likes> likes = new HashSet<>();
	private Set<Share> share = new HashSet<>();
	
}
