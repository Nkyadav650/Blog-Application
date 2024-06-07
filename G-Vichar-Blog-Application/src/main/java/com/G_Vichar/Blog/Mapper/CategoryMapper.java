package com.G_Vichar.Blog.Mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.G_Vichar.Blog.Dto.CategoryDto;
import com.G_Vichar.Blog.Entity.CategoryEntity;
@Configuration
public class CategoryMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	public CategoryEntity categoryDtoToCategoryEntity(CategoryDto categoryDto) {
		return modelMapper.map(categoryDto, CategoryEntity.class);
	}

	public CategoryDto categoryEntityToCategoryDto(CategoryEntity categoryEntity) {
		return modelMapper.map(categoryEntity, CategoryDto.class);
	}

}
