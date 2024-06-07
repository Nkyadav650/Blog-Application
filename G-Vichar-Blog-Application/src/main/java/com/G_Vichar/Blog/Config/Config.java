package com.G_Vichar.Blog.Config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class Config {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
