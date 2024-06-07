package com.G_Vichar.Blog.ServiceImp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.G_Vichar.Blog.Dto.PostsDto;
import com.G_Vichar.Blog.Entity.CategoryEntity;
import com.G_Vichar.Blog.Entity.Comments;
import com.G_Vichar.Blog.Entity.Posts;
import com.G_Vichar.Blog.Entity.User;
import com.G_Vichar.Blog.ExceptionHandler.IdNotFoundException;
import com.G_Vichar.Blog.ExceptionHandler.ResourceNotFoundException;
import com.G_Vichar.Blog.Mapper.PostsMapper;
import com.G_Vichar.Blog.Payload.PageResponse;
import com.G_Vichar.Blog.Repository.CategoryRepo;
import com.G_Vichar.Blog.Repository.CommentsRepo;
import com.G_Vichar.Blog.Repository.PostsRepo;
import com.G_Vichar.Blog.Repository.UserRepo;
import com.G_Vichar.Blog.Service.PostsService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PostsServiceImp implements PostsService {

	@Autowired
	private PostsRepo postsRepo;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private CommentsRepo commentsRepo;
	@Autowired
	private CategoryRepo categoryRepo;
	@Autowired
	private PostsMapper postsMapper;

	@Override
	public PostsDto savePosts(PostsDto postsDto,Long userId,Long categoryId) {
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("Resource Not Available !!"));
		CategoryEntity category= this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Resource Not Available !!"));
		Posts post= this.postsMapper.postsDtoToPosts(postsDto);
		post.setImageName("default.png");
		post.setTimeStamp(LocalDateTime.now());
		post.setUser(user);
		post.setCategory(category);
		Posts posts = postsRepo.save(post);
		return this.postsMapper.postsToPostsDto(posts);
	}

	@Override
	public PostsDto updatePosts(PostsDto postsDto, Long postId) {
		log.info("inside PostsService in G_vichar method upadatePosts : "+postId +" , "+postsDto);
		Posts post=postsRepo.findById(postId).orElseThrow(()->new IdNotFoundException("Id Is Not Avalable !!"));
		post.setCategory(post.getCategory());
		post.setContent(postsDto.getContent());
		post.setImageName(postsDto.getImageName());
		post.setPostId(post.getPostId());
		post.setTimeStamp(LocalDateTime.now());
		post.setTitle(postsDto.getTitle());
		post.setUser(post.getUser());
		log.info("inside PostsService in G_vichar method upadatePosts return post : "+post);
		Posts updatePost=postsRepo.save(post);
		log.info("inside PostsService in G_vichar method upadatePosts return upadtedpost : "+updatePost);
		return this.postsMapper.postsToPostsDto(updatePost);
	
		
	}

	@Override
	public PostsDto deletePosts(Long postId) {
		Posts posts=postsRepo.findById(postId).orElseThrow(()->new IdNotFoundException("Id is Not Availabe !!"));
		postsRepo.delete(posts);
		return this.postsMapper.postsToPostsDto(posts);
	}

	@Override
	public PostsDto getPosts(Long postId) {
		Posts posts=postsRepo.findById(postId).orElseThrow(()->new IdNotFoundException("Id is Not Availabe !!"));	
		Set<Comments> comments=commentsRepo.findAllByPostsPostId(postId);
		posts.setComment(comments);
		return this.postsMapper.postsToPostsDto(posts);
	}

	@Override
	public PageResponse getAllPosts(Integer pageNumber, Integer pageSize) {
		int pageSizes=pageSize;
		int pageNumbers=pageNumber;
		Pageable page= PageRequest.of(pageNumbers, pageSizes);
		Page<Posts>posts=  this.postsRepo.findAll( page);
		
		List <Posts>Allposts=posts.getContent();
		Allposts.forEach(post -> post.setComment(commentsRepo.findAllByPostsPostId(post.getPostId())));
		List<PostsDto>postsList=Allposts.stream().map((post)->this.postsMapper.postsToPostsDto(post)).collect(Collectors.toList());
		
		PageResponse pageResponse=new PageResponse();
		
		pageResponse.setContent(postsList);
		pageResponse.setPageNumber(posts.getNumber());
		pageResponse.setPageSize(posts.getSize());
		pageResponse.setTotalElement(posts.getTotalElements());
		pageResponse.setTotalPage(posts.getTotalPages());
		pageResponse.setLastPage(posts.isLast());
		return pageResponse;
	
	}

	@Override
	public List<PostsDto> getAllPostsByCategory(Long categoryId) {
		CategoryEntity cat=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Resource Not Available !!"));
		List<Posts>posts=this.postsRepo.findByCategory(cat);
		posts.forEach(post -> post.setComment(commentsRepo.findAllByPostsPostId(post.getPostId())));
		List<PostsDto>postsList=posts.stream().map((post)->this.postsMapper.postsToPostsDto(post)).collect(Collectors.toList());
		return postsList;
	
	}

	@Override
	public List<PostsDto> getAllPostsByUser(Long userId) {
		log.info("inside PostsServceImp in G_vichar method getAllPostsByUser "+userId);
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("Resource Not Available !!"));
		log.info("inside PostsServceImp in G_vichar method getAllPostsByUser return user"+user);
		List<Posts>posts=this.postsRepo.findByUser(user);
		posts.forEach(post -> post.setComment(commentsRepo.findAllByPostsPostId(post.getPostId())));
		log.info("inside PostsServceImp in G_vichar method getAllPostsByUser return List Of Posts"+posts);
		List<PostsDto>postsList=new ArrayList<>();
		for(Posts post:posts) {
			postsList.add(postsMapper.postsToPostsDto(post));
		}
		//List<PostsDto>postsList=posts.stream().map((post)->this.postsMapper.postsToPostsDto(post)).collect(Collectors.toList());
		log.info("inside PostsServceImp in G_vichar method getAllPostsByUser return List Of PostsDto"+postsList);
		return postsList;
		
	}

	@Override
	public List<PostsDto> searchPosts(String keyword) {
		List<Posts> posts=this.postsRepo.searchByTitle("%"+keyword+"%");
		posts.forEach(post -> post.setComment(commentsRepo.findAllByPostsPostId(post.getPostId())));
		List<PostsDto>postsList=posts.stream().map((post)->this.postsMapper.postsToPostsDto(post)).collect(Collectors.toList());
		return postsList;
	}
	
	
}
