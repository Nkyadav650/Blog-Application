package com.G_Vichar.Blog.ServiceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.G_Vichar.Blog.Entity.User;
import com.G_Vichar.Blog.ExceptionHandler.ResourceNotFoundException;
import com.G_Vichar.Blog.Repository.UserRepo;

@Service

@Lazy
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepo userRepo;

	// load user from user database

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("LoadUserByUsarName : " + username);
		User user = userRepo.findByEmail(username).orElseThrow(()-> new ResourceNotFoundException("InValid username !!"));
		//UserDetails users = new UserDetails(user.getEmail(), user.getPassword(), user.getRoles());
		System.out.println("CustomeUserDetiails value in side CustomUserDetailService: " + user);
		return user;

	}

}
