package com.G_Vichar.Blog.Mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.G_Vichar.Blog.Dto.UserDto;
import com.G_Vichar.Blog.Entity.User;
@Configuration
public class UserMapper {

	@Autowired
	private  ModelMapper modelMapper;
	
	public  User dtoToUser(UserDto userDto) {
		User user=modelMapper.map(userDto,User.class);
		
		return user;
		
	}
	
	public  UserDto userToDto(User user) {
		UserDto userDto=modelMapper.map(user, UserDto.class);
		
		return userDto;
	}
}
