package com.G_Vichar.Blog.Service;

import java.util.List;

import com.G_Vichar.Blog.Dto.CommentsDto;

public interface CommentService {

	// create
	public CommentsDto saveComment(CommentsDto commentDto,Long postsId , Long userId);

	// update
	public CommentsDto updateComments(CommentsDto category, Long postId);

	// delete
	public CommentsDto deleteComments(Long commentsId);

	// get
	public CommentsDto getCommentsById(Long commentsId);

	// getAll
	public List<CommentsDto> getAllComments(Long postsId);

}
