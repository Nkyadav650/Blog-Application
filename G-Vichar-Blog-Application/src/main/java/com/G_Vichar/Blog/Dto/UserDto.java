package com.G_Vichar.Blog.Dto;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.G_Vichar.Blog.Entity.Subscribe;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	
	private long userId;

	@NotEmpty
	@Column(nullable = false, length = 100)
	@Size(min = 4, message = "User name must be minimum 4 character !!")
	private String userName;

	@NotEmpty
	@Email(message = "Email address not valid !!")	
	private String email;
	
	
	@NotNull
	@Column(nullable = false, length = 100)
	@Size(min=10,max=10, message="Mobile number must be 10 digit !!")
	@Pattern(regexp = "^[0-9]*$",message="number must be digit only and it must be correct Sequence !!")
	private String contact;
	private LocalDateTime timeStamp = LocalDateTime.now();

	@NotEmpty
	//@JsonView(User.class) //it is use to get input and send to entity class
	//@JsonIgnore // it is use to ignore the variable
	// @Pattern(regexp = "")
	private String password;

	private Set<RolesDto> roles = new HashSet<>();
	
	//private Set<Subscribe> subscribe = new HashSet<>();

	@NotEmpty
	private String about;
	
	@JsonIgnore // it is use to ignore the variable
	public String getPassword() {
		return  this.password;
	}
	@JsonProperty
	public void setPassword(String password) {
		this.password=password;
	}
}
