package com.G_Vichar.Blog.Controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.G_Vichar.Blog.Dto.CommentsDto;
import com.G_Vichar.Blog.ExceptionHandler.BadCredentialException;
import com.G_Vichar.Blog.ExceptionHandler.ResourceNotFoundException;
import com.G_Vichar.Blog.Service.CommentService;

@RestController
@RequestMapping("/api/comments")
public class CommentsController {

	@Autowired 
	private CommentService commentService;
	
	Map<String , Object> response= new HashMap<>();
	
	@PostMapping("/saveComments/{postsId}/{userId}")//ok
	public ResponseEntity<Map<String, Object>> saveComments(@RequestBody CommentsDto commentsDto, @PathVariable Long postsId,@PathVariable Long userId)
			throws BadCredentialException {
		CommentsDto saveComments = commentService.saveComment(commentsDto,postsId,userId);
		if (saveComments != null) {
			response.put("NewComment", saveComments);
			response.put("Message", "Data saved seccessfully");
			response.put("Status", HttpStatus.CREATED);
			response.put("Result", "success");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		} else {
			throw new BadCredentialException("Cradential invalid !!");
		}

	}

	// update
	@PutMapping("/update/{postsId}") //ok *******
	public ResponseEntity<Map<String, Object>> updateComments(@RequestBody CommentsDto commentsDto,
			@PathVariable Long postsId) throws BadCredentialException {
		CommentsDto updateComments = commentService.updateComments(commentsDto,postsId);
		if (updateComments != null) {
			response.put("Message", "Data Updated seccessfully");
			response.put("Status", HttpStatus.CREATED);
			response.put("Result", "success");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		} else {
			throw new BadCredentialException("Cradential invalid !!");
		}

	}
	// delete
	@DeleteMapping("/delete/{commentsId}") //ok
	public ResponseEntity<Map<String, Object>> deleteComments(@PathVariable Long commentsId) throws BadCredentialException {
		System.out.println("inside comment controller deleteComment commentsId : "+commentsId);
		CommentsDto deleteComments = commentService.deleteComments(commentsId);
		if (deleteComments!=null) {
			response.clear();
			response.put("Message", "Data Deleted seccessfully");
			response.put("Status", HttpStatus.OK);
			response.put("Result", "success");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} else {
			throw new BadCredentialException("Cradential invalid !!");
		}

	}
	
	// getById
	@GetMapping("/getComments/{commentsId}") //ok
	public ResponseEntity<Map<String, Object>> getCommentsById(@PathVariable Long commentsId) throws BadCredentialException {
		CommentsDto comments= commentService.getCommentsById(commentsId);
		if (comments!=null) {
			response.put("Message", "Data Fetched seccessfully");
			response.put("Data", comments);
			response.put("Status", HttpStatus.OK);
			response.put("Result", "success");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} else {
			throw new BadCredentialException("Cradential invalid !!");
		}

	}
	
	// getAll
	@GetMapping("/getAllComments/{postsId}")//ok
	public ResponseEntity<Map<String, Object>> getAllComments(@PathVariable Long postsId){
		List<CommentsDto> comment= commentService.getAllComments(postsId);
		Set<CommentsDto>comments=new HashSet<>();
		comments.addAll(comment);
		
			response.put("Message", "Data Fetched seccessfully");
			response.put("Data", comments);
			response.put("Status", HttpStatus.OK);
			response.put("Result", "success");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		 

	}
}
