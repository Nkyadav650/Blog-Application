package com.G_Vichar.Blog.ServiceImp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.G_Vichar.Blog.Dto.UserDto;
import com.G_Vichar.Blog.Entity.Roles;
import com.G_Vichar.Blog.Entity.User;
import com.G_Vichar.Blog.ExceptionHandler.ResourceNotFoundException;
import com.G_Vichar.Blog.Mapper.UserMapper;
import com.G_Vichar.Blog.Payload.AppConstants;
import com.G_Vichar.Blog.Repository.RolesRepository;
import com.G_Vichar.Blog.Repository.UserRepo;
import com.G_Vichar.Blog.Service.UserService;
import com.G_Vichar.Blog.Utils.JwtService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImp implements UserService {

	@Autowired
	private UserRepo userRepo;
	@Autowired
	private UserMapper mapper;
	@Autowired
	private RolesRepository rolesRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	  @Autowired private JwtService jwtService;
	 
	@Override
	public UserDto createUser(UserDto userDto) {
		log.info("inside user service in g-vichar application: " + userDto);	
		User user = mapper.dtoToUser(userDto);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		Roles role=this.rolesRepo.findById(AppConstants.ADMIN_USER).get() ;
		user.getRoles().add(role);
		User savedUsers = userRepo.save(user);
		log.info("inside user service in g-vichar application:user data saved " + savedUsers);
		return mapper.userToDto(savedUsers);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Long userId) {
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Id Not Found !!"));
		user.setUserName(userDto.getUserName());
		user.setEmail(userDto.getEmail());
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		user.setAbout(userDto.getAbout());
		user.setTimeStamp(LocalDateTime.now());
		User updatedUser = userRepo.save(user);
		UserDto userDto1 = mapper.userToDto(updatedUser);
		return userDto1;
	}

	@Override
	public UserDto getUserById(Long userId) {
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Id Not Found !!"));
		return mapper.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> user = userRepo.findAll();
		List<UserDto> userDto = user.stream().map(users -> mapper.userToDto(users)).collect(Collectors.toList());
		return userDto;
	}

	@Override
	public void deleteUser(Long userId) {
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Id Not Found !!"));
		userRepo.delete(user);
	}

	@Override
	public String generateToken(String username) {
		return jwtService.generateToken(username);
		
	}

	@Override
	public UserDto registerNewUser(UserDto userDto) {
	
			User user = mapper.dtoToUser(userDto);
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			Roles role=this.rolesRepo.findById(AppConstants.NORMAL_USER).get() ;
			user.getRoles().add(role);
			User newUser=userRepo.save(user);
			return mapper.userToDto(newUser);	
	}

}
