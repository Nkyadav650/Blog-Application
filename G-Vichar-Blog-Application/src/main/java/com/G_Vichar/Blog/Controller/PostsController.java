package com.G_Vichar.Blog.Controller;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.G_Vichar.Blog.Dto.PostsDto;
import com.G_Vichar.Blog.ExceptionHandler.BadCredentialException;
import com.G_Vichar.Blog.ExceptionHandler.ResourceNotFoundException;
import com.G_Vichar.Blog.Payload.AppConstants;
import com.G_Vichar.Blog.Payload.PageResponse;
import com.G_Vichar.Blog.Service.FileService;
import com.G_Vichar.Blog.Service.PostsService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/posts")
@Slf4j
@PropertySource("classpath:application-dev.properties")
public class PostsController {

	@Autowired
	private PostsService postsService;

	@Autowired
	private FileService fileService;

	@Value("${project.image}")
	private String path;

	Map<String, Object> response = new HashMap<>();

	// Create Posts
	 @PostMapping("/savePost/user/{userId}/category/{categoryId}" ) // ok
	public ResponseEntity<Map<String, Object>> savePosts(@RequestBody PostsDto postsDto, @PathVariable Long userId,
			@PathVariable Long categoryId) throws BadCredentialException {
		log.info("inside savepost in post Controller :");
		PostsDto post = postsService.savePosts(postsDto, userId, categoryId);
		if (post != null) {
			response.put("message", "Posts Saved successfully !!");
			response.put("Data", post);
			response.put("status", HttpStatus.CREATED);
			response.put("Result", "success");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		} else {
			throw new BadCredentialException("Credential invalid !!");
		}
	}

	// get by user

	@GetMapping("/user/posts/{userId}") // ok
	public ResponseEntity<Map<String, Object>> getPostsByUserId(@PathVariable Long userId)
			throws ResourceNotFoundException {
		log.info("inside PostsController in g_vichar method getPostsByUserId" + userId);
		List<PostsDto> postsDto = postsService.getAllPostsByUser(userId);
		log.info("inside PostsController in g_vichar  method getPostsByUserId Return data " + postsDto);
		if (postsDto != null) {
			response.put("Message", "Data Fetched seccessfully");
			response.put("Data", postsDto);
			response.put("Status", HttpStatus.OK);
			response.put("Result", "success");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} else {
			throw new ResourceNotFoundException("Cradential invalid !!");
		}

	}

	// get BY Category
	@GetMapping("/category/{categoryId}/posts") // ok
	public ResponseEntity<Map<String, Object>> getPostsByCategoryId(@PathVariable Long categoryId)
			throws ResourceNotFoundException {
		List<PostsDto> postsDto = postsService.getAllPostsByCategory(categoryId);
		if (postsDto != null) {
			response.put("Message", "Data Fetched seccessfully");
			response.put("Data", postsDto);
			response.put("Status", HttpStatus.OK);
			response.put("Result", "success");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} else {
			throw new ResourceNotFoundException("Cradential invalid !!");
		}

	}

	// get All Posts
	@GetMapping("/allPosts") // ok
	public ResponseEntity<Map<String, Object>> getAllPosts(
			@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageSize,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageNumber,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir)
			throws ResourceNotFoundException {
		PageResponse postsDto = postsService.getAllPosts(pageSize, pageNumber);
		if (postsDto != null) {
			response.put("Message", "Data Fetched seccessfully");
			response.put("Data", postsDto);
			response.put("Status", HttpStatus.OK);
			response.put("Result", "success");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} else {
			throw new ResourceNotFoundException("Cradential invalid !!");
		}

	}

	// get by posts Id
	@GetMapping("/getBy/{postsId}") // ok
	public ResponseEntity<Map<String, Object>> getByPosts(@PathVariable Long postsId) throws ResourceNotFoundException {
		PostsDto postsDto = postsService.getPosts(postsId);
		if (postsDto != null) {
			response.put("Message", "Data Fetched seccessfully");
			response.put("Data", postsDto);
			response.put("Status", HttpStatus.OK);
			response.put("Result", "success");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} else {
			throw new ResourceNotFoundException("Cradential invalid !!");
		}

	}

	// update posts posts Id
	@PutMapping("/updatePosts/{postsId}")//ok
	public ResponseEntity<Map<String, Object>> updatePosts(@RequestBody PostsDto postsDto, @PathVariable Long postsId)
			throws ResourceNotFoundException {
		PostsDto postDto = postsService.updatePosts(postsDto, postsId);
		if (postDto != null) {
			response.put("Message", "post updated seccessfully");
			response.put("Data", postDto);
			response.put("Status", HttpStatus.OK);
			response.put("Result", "success");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} else {
			throw new ResourceNotFoundException("Cradential invalid !!");
		}

	}

	// delete BY posts iD
	@DeleteMapping("/deletePosts/{postsId}")//ok
	public ResponseEntity<Map<String, Object>> deleteByPosts(@PathVariable Long postsId)
			throws ResourceNotFoundException {
		PostsDto postsDto = postsService.deletePosts(postsId);
		if (postsDto != null) {
			response.put("Message", "Post Delete seccessfully");
			response.put("Data", postsDto);
			response.put("Status", HttpStatus.OK);
			response.put("Result", "success");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} else {
			throw new ResourceNotFoundException("Cradential invalid !!");
		}

	}

	// search by
	@GetMapping("/search/{keywords}") //ok
	public ResponseEntity<Map<String, Object>> getPostsBySearch(@PathVariable("keywords") String keywords)
			throws ResourceNotFoundException {
		List<PostsDto> postsDto = postsService.searchPosts(keywords);
		if (postsDto != null) {
			response.put("Message", "Data Fetched seccessfully");
			response.put("Data", postsDto);
			response.put("Status", HttpStatus.OK);
			response.put("Result", "success");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} else {
			throw new ResourceNotFoundException("Cradential invalid !!");
		}
	}

	// post image upload
	@PutMapping("/image/upload/{postsId}")  //ok
	public ResponseEntity<Map<String, Object>> uploadImage(@RequestParam("image") MultipartFile image,
			@PathVariable Long postsId) throws Exception {
		System.out.println("Hello"+path);
		String uploadImage = this.fileService.uploadImage(path, image);
		PostsDto postsDto = this.postsService.getPosts(postsId);
		postsDto.setImageName(uploadImage);
		PostsDto updatePosts = this.postsService.updatePosts(postsDto, postsId);
		if (updatePosts != null) {
			response.put("Message", "Data Fetched seccessfully");
			response.put("Data", updatePosts);
			response.put("Status", HttpStatus.OK);
			response.put("Result", "success");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} else {
			throw new ResourceNotFoundException("Cradential invalid !!");
		}
	}

	// method to serve files
	@GetMapping(value = "/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE) //ok
	public void getPostsBySearch(@PathVariable("imageName") String imageName, HttpServletResponse responses)
			throws Exception {
		InputStream resource = this.fileService.getResource(path, imageName);
		responses.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, responses.getOutputStream());
	}

}
