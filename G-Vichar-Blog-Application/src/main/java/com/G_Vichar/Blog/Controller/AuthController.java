package com.G_Vichar.Blog.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.G_Vichar.Blog.Dto.UserDto;
import com.G_Vichar.Blog.Entity.User;
import com.G_Vichar.Blog.ExceptionHandler.BadCredentialException;
import com.G_Vichar.Blog.Mapper.UserMapper;
import com.G_Vichar.Blog.Payload.JwtRequest;
import com.G_Vichar.Blog.Service.UserService;
import com.G_Vichar.Blog.ServiceImp.CustomUserDetailsService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/auth")
@Slf4j
public class AuthController {


	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private CustomUserDetailsService userDetailsService;
	@Autowired
	private UserMapper mapper;
	@Autowired
	private UserService userService;
	
	Map<String, Object> response = new HashMap<>();
	
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> getToken(@Valid @RequestBody JwtRequest request) throws BadCredentialException {
		System.out.println(request.getEmail() + "," + request.getPassword());
		User user = (User) userDetailsService.loadUserByUsername(request.getEmail());
		System.out.println("userDetails in genToken in authController : "+user);
		try {
			authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
			response.put("Token", userService.generateToken(request.getEmail()));
			response.put("user", mapper.userToDto(user));
			response.put("status", HttpStatus.OK);
			response.put("Result", "Success");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

		} catch (Exception ex) {
			throw new BadCredentialException("invalid Password !!");
		}

	}
	// for Register new user
	@PostMapping("/register") // ok
	public ResponseEntity<Map<String, Object>> registerNewUser(@Valid @RequestBody UserDto userDto) throws Exception {
		System.out.println("UserController.createUser()" + userDto);
		log.info("inside user controller in g-vichar application: " + userDto);
		UserDto userDt = userService.registerNewUser(userDto);
		if (userDt != null) {
			response.put("Message", "User Created !!");
			response.put("Data", userDto);
			response.put("status", HttpStatus.CREATED);
			response.put("Result", "Success");
			return new ResponseEntity<>(response, HttpStatus.CREATED);

		} else {
			throw new BadCredentialException("Credential invalid !!");
		}
	}

}
