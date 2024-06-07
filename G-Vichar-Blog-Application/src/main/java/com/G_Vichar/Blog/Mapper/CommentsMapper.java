package com.G_Vichar.Blog.Mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.G_Vichar.Blog.Dto.CommentsDto;
import com.G_Vichar.Blog.Entity.Comments;

@Configuration
public class CommentsMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	public Comments commentsDtoToComments(CommentsDto commentsDto) {
		return modelMapper.map(commentsDto, Comments.class);
	}
	
	public CommentsDto commentsToCommentsDto(Comments comments) {
		return modelMapper.map(comments, CommentsDto.class);
	}

}
