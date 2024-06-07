package com.G_Vichar.Blog.Service;

import java.util.List;

import com.G_Vichar.Blog.Dto.UserDto;

public interface UserService {

	public UserDto registerNewUser(UserDto userDto);
	public UserDto createUser(UserDto userDto);
	public UserDto updateUser(UserDto userDto,Long userId);
	public UserDto getUserById(Long userId);
	public List<UserDto> getAllUsers();
	public void deleteUser(Long userId);
	public String generateToken(String username);
}
