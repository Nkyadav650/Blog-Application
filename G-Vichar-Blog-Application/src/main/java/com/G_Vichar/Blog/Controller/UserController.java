package com.G_Vichar.Blog.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.G_Vichar.Blog.Dto.UserDto;
import com.G_Vichar.Blog.ExceptionHandler.BadCredentialException;
import com.G_Vichar.Blog.ExceptionHandler.ResourceNotFoundException;
import com.G_Vichar.Blog.Service.UserService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/users")
@Slf4j
public class UserController {

	@Autowired
	private UserService userService;
	Map<String, Object> response = new HashMap<>();

	// POST - Create User
	@PostMapping("/saveUser") // ok
	public ResponseEntity<Map<String, Object>> createUser(@Valid @RequestBody UserDto userDto) throws Exception {
		System.out.println("UserController.createUser()" + userDto);
		log.info("inside user controller in g-vichar application: " + userDto);
		UserDto userDt = userService.createUser(userDto);
		if (userDt != null) {
			response.put("Message", "User Created !!");
			response.put("status", HttpStatus.CREATED);
			response.put("Result", "Success");
			return new ResponseEntity<>(response, HttpStatus.CREATED);

		} else {
			throw new BadCredentialException("Credential invalid !!");
		}
	}

	// PUT - Update user
	@PutMapping("/update/{userId}") // ok
	public ResponseEntity<Map<String, Object>> updateUser(@RequestBody UserDto userDto, @PathVariable long userId)
			throws BadCredentialException {
		UserDto userDto1 = userService.updateUser(userDto, userId);
		if (userDto1 != null) {
			response.put("Message", "User updated !!");
			response.put("status", HttpStatus.OK);
			response.put("Result", "Success");
			return new ResponseEntity<>(response, HttpStatus.OK);

		} else {
			throw new BadCredentialException("Credential invalid !!");
		}
	}

	// Delete - Delete user

	@PreAuthorize("hashRole('admin')")
	@DeleteMapping("delete/{userId}") // ok
	public ResponseEntity<Map<String, Object>> deleteUser(@PathVariable long userId) {
		userService.deleteUser(userId);
		response.put("message", "Data deleted successfully !!");
		response.put("status", HttpStatus.OK);
		response.put("Result", "Success");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// get users By Ids
	@GetMapping("/getUser/{userId}") // ok
	public ResponseEntity<Map<String, Object>> getUserById(@PathVariable long userId) {
		UserDto user = userService.getUserById(userId);
		if (user != null) {
			response.put("Data", user);
			response.put("status", HttpStatus.OK);
			response.put("Result", "Success");
			return new ResponseEntity<>(response, HttpStatus.OK);

		} else {
			throw new ResourceNotFoundException("Resource not availale !!");
		}
	}

	// Get All Users
	@GetMapping("/AllUser") // ok
	public ResponseEntity<Map<String, Object>> getAllUser() {
		List<UserDto> user = userService.getAllUsers();
		if (user != null) {
			response.put("Data", user);
			response.put("total", user.size());
			response.put("status", HttpStatus.OK);
			response.put("Result", "Success");
			return new ResponseEntity<>(response, HttpStatus.OK);

		} else {
			throw new ResourceNotFoundException("Resource not availale !!");
		}
	}

	
}
