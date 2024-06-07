package com.G_Vichar.Blog.Service;

import java.util.List;

import com.G_Vichar.Blog.Dto.PostsDto;
import com.G_Vichar.Blog.Payload.PageResponse;

public interface PostsService {

	//create
	public PostsDto savePosts(PostsDto postsDto,Long userId, Long categoryId);
	
	//update
	public PostsDto updatePosts(PostsDto postsDto,Long postId);

	//delete
	public PostsDto deletePosts(Long postId);

	//getById
	public PostsDto getPosts(Long postId);

	//getAll
	public PageResponse getAllPosts(Integer pageNumber, Integer pageSize);
	
	//getAll Posts By Category
	public List<PostsDto> getAllPostsByCategory(Long categoryId);
	
	//getAll Posts By User
	public List<PostsDto> getAllPostsByUser(Long userId);
	
	//search posts
	public List<PostsDto> searchPosts(String keeword);

}
