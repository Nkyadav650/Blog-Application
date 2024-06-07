package com.G_Vichar.Blog.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.G_Vichar.Blog.Dto.LikesDto;
import com.G_Vichar.Blog.Dto.ShareDto;
import com.G_Vichar.Blog.Dto.SubscribeDto;
import com.G_Vichar.Blog.ExceptionHandler.BadCredentialException;
import com.G_Vichar.Blog.Service.Likes_Share_Subscribe_Service;

@RestController
@RequestMapping("/api/likes/share/subscribe")
public class Likes_Share_Subscribe_Controller {

	@Autowired
	private Likes_Share_Subscribe_Service lssService;

	Map<String, Object> response = new HashMap<>();

	@PostMapping("/saveLikes/{postsId}/{userId}")
	public ResponseEntity<Map<String, Object>> createLikes(@RequestBody LikesDto likesDto, @PathVariable Long postsId,@PathVariable Long userId)
			throws BadCredentialException {
		LikesDto savedLikes = lssService.createLikes(likesDto, postsId,userId);
		if (savedLikes != null) {
			response.put("Message", "likes created successfully !!");
			response.put("status", HttpStatus.CREATED.value());
			response.put("Result", "Success");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		} else {
			throw new BadCredentialException("Invalid credentials !!");
		}
	}

	@GetMapping("getAllLikes/{postsId}")
	public ResponseEntity<Map<String, Object>> getAllLikes(@PathVariable Long postsId) throws BadCredentialException {
		List<LikesDto> allLikes = lssService.getAllLikes(postsId);
		if (allLikes != null) {
			response.put("Data", allLikes);
			response.put("status", HttpStatus.OK.value());
			response.put("Result", "Success");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} else {
			throw new BadCredentialException("Invalid credentials !!");
		}
	}

	@DeleteMapping("deleteLikes/{id}")
	public ResponseEntity<Map<String, Object>> deleteLikes(@PathVariable Long id) throws BadCredentialException {
		lssService.deleteLikes(id);

		response.put("Message", "likes created successfully !!");
		response.put("status", HttpStatus.OK.value());
		response.put("Result", "Success");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@PostMapping("saveShare/{postsId}")
	public ResponseEntity<Map<String, Object>> createShare(@RequestBody ShareDto shareDto, @PathVariable Long postsId)
			throws BadCredentialException {
		ShareDto savedShare = lssService.createShare(shareDto, postsId);
		if (savedShare != null) {
			response.put("Message", "likes created successfully !!");
			response.put("status", HttpStatus.CREATED.value());
			response.put("Result", "Success");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		} else {
			throw new BadCredentialException("Invalid credentials !!");
		}
	}

	@GetMapping("getAllShare/{postsId}")
	public ResponseEntity<Map<String, Object>> getAllShare(@PathVariable Long postsId) throws BadCredentialException {
		List<ShareDto> shareList = lssService.getAllShare(postsId);
		if (shareList != null) {
			response.put("Data", shareList);
			response.put("status", HttpStatus.OK.value());
			response.put("Result", "Success");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} else {
			throw new BadCredentialException("Invalid credentials !!");
		}
	}

	@DeleteMapping("deleteShare/{id}")
	public ResponseEntity<Map<String, Object>> deleteShare(@PathVariable Long id) throws BadCredentialException {
		lssService.deleteShare(id);

		response.put("Message", "Share Deleted successfully !!");
		response.put("status", HttpStatus.OK.value());
		response.put("Result", "Success");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@PostMapping("saveSubscriber/{userId}")
	public ResponseEntity<Map<String, Object>> createSubscribe(@RequestBody SubscribeDto subscribeDto, @PathVariable Long userId)
			throws BadCredentialException {
		SubscribeDto savedSubscriber = lssService.createSubscribe(subscribeDto, userId);
		if (savedSubscriber != null) {
			response.put("Message", "subscriber created successfully !!");
			response.put("status", HttpStatus.CREATED.value());
			response.put("Result", "Success");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		} else {
			throw new BadCredentialException("Invalid credentials !!");
		}
	}

	@PostMapping("getAllsubscriber/{userId}")
	public ResponseEntity<Map<String, Object>> getAllsubscriber(@PathVariable Long userId)
			throws BadCredentialException {
		List<SubscribeDto> subscriberList = lssService.getAllSubscribe( userId);
		if (subscriberList != null) {
			response.put("Data",subscriberList);
			response.put("status", HttpStatus.OK.value());
			response.put("Result", "Success");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		} else {
			throw new BadCredentialException("Invalid credentials !!");
		}
	}
	
	@DeleteMapping("deleteSubscriber/{id}")
	public ResponseEntity<Map<String, Object>> deleteSubscriber(@PathVariable Long id) throws BadCredentialException {
		lssService.deleteSubscribe(id);

		response.put("Message", "Share Deleted successfully !!");
		response.put("status", HttpStatus.OK.value());
		response.put("Result", "Success");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}
