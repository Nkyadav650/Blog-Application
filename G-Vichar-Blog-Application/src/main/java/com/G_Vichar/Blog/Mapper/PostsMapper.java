package com.G_Vichar.Blog.Mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.G_Vichar.Blog.Dto.PostsDto;
import com.G_Vichar.Blog.Entity.Posts;

@Configuration
public class PostsMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	public PostsDto postsToPostsDto(Posts post) {
		return modelMapper.map(post, PostsDto.class);
	}
	
	public Posts postsDtoToPosts(PostsDto postDto) {
		return modelMapper.map(postDto, Posts.class);
	}
}
