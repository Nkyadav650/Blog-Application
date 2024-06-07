package com.G_Vichar.Blog.ServiceImp;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.G_Vichar.Blog.Dto.CommentsDto;
import com.G_Vichar.Blog.Entity.Comments;
import com.G_Vichar.Blog.Entity.Posts;
import com.G_Vichar.Blog.Entity.User;
import com.G_Vichar.Blog.ExceptionHandler.IdNotFoundException;
import com.G_Vichar.Blog.ExceptionHandler.ResourceNotFoundException;
import com.G_Vichar.Blog.Mapper.CommentsMapper;
import com.G_Vichar.Blog.Repository.CommentsRepo;
import com.G_Vichar.Blog.Repository.PostsRepo;
import com.G_Vichar.Blog.Repository.UserRepo;
import com.G_Vichar.Blog.Service.CommentService;

@Service
public class CommentsServiceImp implements CommentService {

	@Autowired
	private PostsRepo postRepo;
	@Autowired
	private CommentsRepo commentsRepo;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private CommentsMapper commentsMapper;

	@Override
	public CommentsDto saveComment(CommentsDto commentDto, Long postsId ,Long userId) {
		Posts post = this.postRepo.findById(postsId).orElseThrow(() -> new IdNotFoundException("postId Not available !!"));
		Comments comments = commentsMapper.commentsDtoToComments(commentDto);
		comments.setPosts(post);
		User user=userRepo.findById(userId).orElseThrow(() -> new IdNotFoundException("userId Not available !!"));;
		comments.setUser(user);
		Comments saveComments = commentsRepo.save(comments);
		return commentsMapper.commentsToCommentsDto(saveComments);
	}

	@Override
	public CommentsDto updateComments(CommentsDto commentDto, Long commentId) {
		Comments comments = commentsRepo.findById(commentId)
				.orElseThrow(() -> new IdNotFoundException("Id Not Available !!"));
		comments.setContent(commentDto.getContent());
		Comments saveComments = commentsRepo.save(comments);

		return this.commentsMapper.commentsToCommentsDto(saveComments);
	}

	@Override
	public CommentsDto deleteComments(Long commentsId) {
		Comments comment = commentsRepo.findById(commentsId)
				.orElseThrow(() -> new ResourceNotFoundException("Resource not Available !!"));
		commentsRepo.delete(comment);
		return this.commentsMapper.commentsToCommentsDto(comment);
	}

	@Override
	public CommentsDto getCommentsById(Long commentsId) {
		Comments comment = commentsRepo.findById(commentsId)
				.orElseThrow(() -> new ResourceNotFoundException("Resource not Available !!"));
		return this.commentsMapper.commentsToCommentsDto(comment);
	}

	@Override
	public List<CommentsDto> getAllComments(Long postsId) {
		Set<Comments> comments = commentsRepo.findAllByPostsPostId(postsId);
		List<CommentsDto> commentsList = comments.stream()
				.map((comment) -> commentsMapper.commentsToCommentsDto(comment)).collect(Collectors.toList());
		return commentsList;
	}

}
